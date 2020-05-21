/**
 * 
 */
package components;

import utilities.GameDriver;
import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

/**
 * @author Sophie Krimberg
 *
 */
/**
 * @author krsof
 *
 */
public class Vehicle implements Utilities, Timer, Runnable {
	private int id;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private long timeFromRouteStart;
	private static int objectsCount = 1;
	private long timeOnCurrentPart;
	private Road lastRoad;
	private String status;

	/**
	 * Random Constructor
	 * 
	 * @param currentLocation
	 */
	public Vehicle(Road currentLocation) {

		id = objectsCount++;
		vehicleType = currentLocation.getVehicleTypes()[getRandomInt(0, currentLocation.getVehicleTypes().length - 1)];
		System.out.println();
		successMessage(this.toString());
		currentRoute = new Route(currentLocation, this); // creates a new route for the vehicle and checks it in
		lastRoad = currentLocation;
		status = null;

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
			synchronized (currentRoutePart) {
				try {
					currentRoutePart.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
		while (GameDriver.running) {
			try {
				Thread.sleep(GameDriver.iterationTime);
			} catch (InterruptedException e) {
				System.out.println("Sleep failed");
				e.printStackTrace();
			}
			move();
		}

	}

}
