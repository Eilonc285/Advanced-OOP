package components;

import java.util.Random;

import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

public class Vehicle implements Timer, Utilities {
	private int id;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private int timeFromRouteStart;
	private int timeOnCurrentPart;
	private static int objectsCount;
	private Road lastRoad;
	private String status;

	static {
		objectsCount = 0;
	}

	/**
	 * Constructor initiates the fields, uses the given road as the starting
	 * location for the vehicle and its route.
	 * 
	 * @param road: The starting location of the vehicle.
	 */
	public Vehicle(Road road) {
		Random rand = new Random();
		this.id = ++Vehicle.objectsCount;
		this.currentRoutePart = road;
		this.lastRoad = road;
		this.vehicleType = VehicleType.values()[rand.nextInt(VehicleType.values().length)];
		this.timeFromRouteStart = 0;
		this.timeOnCurrentPart = 0;
		this.status = "waiting";
		System.out.printf("\n%s has been created\n", this.toString());
		this.currentRoute = new Route(this.currentRoutePart, this);
	}

	/**
	 * Sets the id of the vehicle.
	 * 
	 * @param id: The id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the vehicle type of the vehicle.
	 * 
	 * @param vehicleType: The vehicle type to be set.
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Sets the current route of the vehicle.
	 * 
	 * @param currentRoute: The route to be set.
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	/**
	 * Sets the current route part of the vehicle. Could be a road or a junction.
	 * 
	 * @param currentRoutePart: The route part to be set.
	 */
	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}

	/**
	 * Sets the time from the start of the route.
	 * 
	 * @param timeFromRouteStart: The time to be set.
	 */
	public void setTimeFromRouteStart(int timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}

	/**
	 * Sets the time on the current route part.
	 * 
	 * @param timeOnCurrentPart: The time to be set.
	 */
	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
		this.timeOnCurrentPart = timeOnCurrentPart;
	}

	/**
	 * Sets the number of existing objects of this class.
	 * 
	 * @param objectsCount: The number of objects to be set.
	 */
	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}

	/**
	 * Sets the last road the vehicle drove on.
	 * 
	 * @param lastRoad: The road to be set.
	 */
	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}

	/**
	 * Sets the status of the vehicle.
	 * 
	 * @param status: The status to be set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * returns the id of the vehicle.
	 * 
	 * @return: The id of the vehicle.
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns the vehicle type of the vehicle.
	 * 
	 * @return: The type to be returned.
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Returns the current route of the vehicle.
	 * 
	 * @return: The current route to be returned.
	 */
	public Route getCurrentRoute() {
		return currentRoute;
	}

	/**
	 * Returns the current route part of the vehicle. Could be a road or a junction.
	 * 
	 * @return: The current route part.
	 */
	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}

	/**
	 * Returns the time passed from the start of the route.
	 * 
	 * @return: The passed time.
	 */
	public int getTimeFromRouteStart() {
		return timeFromRouteStart;
	}

	/**
	 * Return the time passed on the current route part.
	 * 
	 * @return: The current part time.
	 */
	public int getTimeOnCurrentPart() {
		return timeOnCurrentPart;
	}

	/**
	 * Returns the object count of this class.
	 * 
	 * @return: The number of existing objects.
	 */
	public static int getObjectsCount() {
		return objectsCount;
	}

	/**
	 * Returns the last road the vehicle drove on.
	 * 
	 * @return: The last road.
	 */
	public Road getLastRoad() {
		return lastRoad;
	}

	/**
	 * Returns the current status of the vehicle.
	 * 
	 * @return: The status of the vehicle.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method is called by the incrementDrivingTime method every passing pulse.
	 * This method sets the time and loacation of the vehicle in the next pulse.
	 */
	public void move() {
		if (this.currentRoutePart
				.equals(this.currentRoute.getRouteParts().get(this.currentRoute.getRouteParts().size() - 1))
				&& this.currentRoutePart.canLeave(this)) {
			Route newRoute = ((Route) this.currentRoute.findNextPart(this));
			this.currentRoutePart.checkOut(this);
			this.currentRoute.checkOut(this);
			this.currentRoute = newRoute;
			this.currentRoute.checkIn(this);
			this.currentRoutePart = this.currentRoute.getRouteParts().get(0);
			this.currentRoutePart.checkIn(this);
		} else if (this.currentRoutePart.canLeave(this)) {
			RouteParts nextPart = this.currentRoute.findNextPart(this);
			this.currentRoutePart.checkOut(this);
			this.currentRoutePart = nextPart;
			this.currentRoutePart.checkIn(this);
		} else {
			if (this.currentRoutePart instanceof Road) {
				double timeToArrive = ((Road) this.currentRoutePart).calcEstimatedTime(this) - this.timeOnCurrentPart;
				System.out.printf("- is still moving on %s, time to arrive: %f\n",
						((Road) this.currentRoutePart).toString(), timeToArrive);
			} else {
				if (this.currentRoutePart instanceof LightedJunction) {
					if (((LightedJunction) this.currentRoutePart).getLights().isTrafficLightsOn()) {
						System.out.printf("- is waiting at %s for green light\n", this.currentRoutePart.toString());
					}
				}
				System.out.printf("- is waiting at %s- there are previous cars on the same road.\n",
						this.getCurrentRoutePart().toString());
			}
		}
	}

	/**
	 * This method is called by the same method in the driving class, which advances
	 * the system time in all the time keeping elements. This method increases the
	 * total time and time on current part by 1 and calls the move method in order
	 * to advance in the route.
	 */
	public void incrementDrivingTime() {
		System.out.printf("\n%s\n", this.toString());
		move();
		this.timeFromRouteStart++;
		this.timeOnCurrentPart++;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The string representation.
	 */
	@Override
	public String toString() {
		return String.format("Vehicle %d: %s, average speed: %d", this.id, this.vehicleType.toString(),
				this.vehicleType.getAverageSpeed());
	}

	/**
	 * Determines if the given objects equals this object.
	 * 
	 * @param other: The object to check if it equals this one.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Vehicle) {
			return this.id == ((Vehicle) other).getId() && this.vehicleType.equals(((Vehicle) other).getVehicleType())
					&& this.currentRoutePart.equals(((Vehicle) other).getCurrentRoutePart());
		}
		return false;
	}

}
