/**
 * 
 */
package components;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;

import utilities.GameDriver;
import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

/**
 * @author Sophie Krimberg, Nir Barel, Eilon Cohen
 *
 */
/**
 * @author krsof
 *
 */
public class Vehicle implements Utilities, Timer, Runnable {
	private int id;
	private VehicleType vehicleType;
	private float independantSpeed;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private long timeFromRouteStart;
	private static int objectsCount = 1;
	private long timeOnCurrentPart;
	private Road lastRoad;
	private String status;
	private Color carColor = Color.BLACK;
	private boolean updatedBigBrother = false;

	/**
	 * Random Constructor
	 * 
	 * @param currentLocation
	 */
	public Vehicle(Road currentLocation) {

		id = objectsCount++;
		vehicleType = currentLocation.getVehicleTypes()[getRandomInt(0, currentLocation.getVehicleTypes().length - 1)];
		independantSpeed = vehicleType.getAverageSpeed();
		if (GameDriver.isPConsole())
			System.out.println();
		successMessage(this.toString());
		currentRoute = new Route(currentLocation, this); // creates a new route for the vehicle and checks it in
		lastRoad = currentLocation;
		status = null;

	}

	public Vehicle(Road currentLocation, VehicleType vehicleType) {
		this(currentLocation);
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the currentRoute
	 */
	public Route getCurrentRoute() {
		return currentRoute;
	}

	/**
	 * @param currentRoute the currentRoute to set
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	/**
	 * @return the currentRoutePart
	 */
	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}

	/**
	 * @param currentRoutePart the currentRoutePart to set
	 */
	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}

	/**
	 * @return the timeFromRouteStart
	 */
	public long getTimeFromRouteStart() {
		return timeFromRouteStart;
	}

	/**
	 * @param timeFromRouteStart the timeFromRouteStart to set
	 */
	public void setTimeFromRouteStart(long timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}

	/**
	 * @return the timeOnCurrentPart
	 */
	public long getTimeOnCurrentPart() {
//		return timeOnCurrentPart;
		return System.currentTimeMillis() - this.timeOnCurrentPart;
	}

	/**
	 * @param timeOnCurrentPart the timeOnCurrentPart to set
	 */
	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
//		this.timeOnCurrentPart = timeOnCurrentPart;
		this.timeOnCurrentPart = System.currentTimeMillis();
	}

	/**
	 * @return the lastRoad
	 */
	public Road getLastRoad() {
		return lastRoad;
	}

	/**
	 * @param lastRoad the lastRoad to set
	 */
	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the objectsCount
	 */
	public static int getObjectsCount() {
		return objectsCount;
	}

	public float getIndependantSpeed() {
		return independantSpeed;
	}

	public void setIndependantSpeed(float independantSpeed) {
		this.independantSpeed = independantSpeed;
	}

	@Override
	public void incrementDrivingTime() {
		timeFromRouteStart++;
		timeOnCurrentPart++;
		move();
	}

	/**
	 * controls the vehicle moving from one route part to the next one
	 * 
	 */
	public void move() {
		while (!currentRoutePart.canLeave(this)) {
			currentRoutePart.stayOnCurrentPart(this);
			if (currentRoutePart instanceof LightedJunction) {
				synchronized (((LightedJunction) currentRoutePart).getLights()) {
					try {
						((LightedJunction) currentRoutePart).getLights().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				synchronized (currentRoutePart) {
					try {
						currentRoutePart.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (!updatedBigBrother) {
			BigBrother.getBigBrother().update(this);
			updatedBigBrother = true;
		}
		currentRoutePart.checkOut(this);
		currentRoute.findNextPart(this).checkIn(this);

	}

	@Override
	public String toString() {
		return new String("Vehicle " + id + ": " + getVehicleType().name() + ", average speed: "
				+ getVehicleType().getAverageSpeed());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Vehicle other = (Vehicle) obj;
		if (this.currentRoute != other.currentRoute || this.currentRoutePart != other.currentRoutePart
				|| this.id != other.id || this.lastRoad != other.lastRoad || this.status != other.status
				|| this.timeFromRouteStart != other.timeFromRouteStart
				|| this.timeOnCurrentPart != other.timeOnCurrentPart || this.vehicleType != other.vehicleType)
			return false;
		return true;
	}

	/**
	 * @param objectsCount the objectsCount to set
	 */
	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}

	/**
	 * The method to be run by the thread
	 */
	@Override
	public void run() {
		while (GameDriver.isRunning()) {
			while (GameDriver.getPause()) {
				synchronized (GameDriver.class) {
					try {
						GameDriver.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(GameDriver.getIterationTime());
			} catch (InterruptedException e) {
				if (GameDriver.isPConsole())
					System.out.println("Sleep failed");
				e.printStackTrace();
			}
			move();
		}

	}

	public void setCarColor(Color c) {
		carColor = c;
	}

	public Color getCarColor() {
		return carColor;
	}

	public static void resetVehicleCount() {
		Vehicle.objectsCount = 0;
	}

	public void resetUpdateBool() {
		updatedBigBrother = false;
	}

	public void noticeReport(int vicId, String path) {
		ReadWriteLock fileLock = Moked.getMoked().getFileLock();
		Report report = new Report(-1, -1);
		fileLock.readLock().lock();
		try {
			FileReader reportsReader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(reportsReader);
			String currentLine;
			while ((currentLine = bReader.readLine()) != null) {
				report = Report.constructFromString(currentLine);
				if (report.getVehicleId() == id && report.isAuthorized() == false) {
					break;
				}
			}
			bReader.close();
			reportsReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileLock.readLock().unlock();
		}
		Moked.getMoked().approveReport(report.getReportId());
	}
}
