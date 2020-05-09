package components;

import java.util.ArrayList;

import utilities.Point;
import utilities.VehicleType;

/**
 * A class that represents a junction with no traffic lights.
 * 
 * @author Eilon
 *
 */
public class Junction extends Point implements RouteParts {
	private static int objectCounts;
	private ArrayList<Road> enteringRoads;
	private ArrayList<Road> exitingRoads;
	private String junctionName;

	static {
		objectCounts = 0;
	}

	/**
	 * This constructor gives a random value to the point position, and generates a
	 * name from this objects index.
	 */
	public Junction() {
		super();
		this.enteringRoads = new ArrayList<Road>();
		this.exitingRoads = new ArrayList<Road>();
		Junction.objectCounts++;
		this.junctionName = String.format("%s", String.valueOf(Junction.objectCounts));
		System.out.printf("%s has been created.\n", this.toString());
	}

	/**
	 * This constructor sets the objects values from the given parameters.
	 * 
	 * @param junctionName: The objects name.
	 * @param x:            The position of the junction on the x axis.
	 * @param y:            The position of the junction on the y axis.
	 */
	public Junction(String junctionName, double x, double y) {
		super(x, y);
		this.enteringRoads = new ArrayList<Road>();
		this.exitingRoads = new ArrayList<Road>();
		this.junctionName = junctionName;
		Junction.objectCounts++;
	}

	/**
	 * Add a Road object to the entering roads array.
	 * 
	 * @param road: The road to be added.
	 */
	public void addEnteringRoad(Road road) {
		this.enteringRoads.add(road);
	}

	/**
	 * Add a Road object to the exiting roads array.
	 * 
	 * @param road: The road to be added.
	 */
	public void addExitingRoad(Road road) {
		this.exitingRoads.add(road);
	}

	/**
	 * This method receives a vehicle object as an argument and returns the maximum
	 * time that vehicle would need to wait at this junction before continuing
	 * driving. The calculation is the amount of entering roads with a lower index
	 * in the entering roads array, plus one.
	 * 
	 * @param obj: The vehicle to estimate wait time for.
	 */
	public double calcEstimatedTime(Object obj) {
		if (obj instanceof Vehicle) {
			for (int i = 0; i < ((Vehicle) obj).getCurrentRoute().getRouteParts().size(); i++) {
				if (((Vehicle) obj).getCurrentRoute().getRouteParts().get(i).equals(this)) {
					int enterRoadIndex = this.enteringRoads
							.indexOf(((Vehicle) obj).getCurrentRoute().getRouteParts().get(i - 1));
					return (double) (enterRoadIndex + 1);
				}
			}
			return (double) 0;
		} else {
			return (double) 0;
		}
	}

	/**
	 * This method returns a boolean of if the given vehicle can leave this traffic
	 * section (junction).
	 * 
	 * @param vehicle: The vehicle for which to check if it can leave.
	 */
	public boolean canLeave(Vehicle vehicle) {
		return checkAvailability(vehicle);
	}

	public String getJunctionName() {
		return junctionName;
	}

	public void setJunctionName(String junctionName) {
		this.junctionName = junctionName;
	}

	/**
	 * This method is a helper method for the canLeave method. This method checks if
	 * there are any exiting roads and if the given vehicle is in the highest
	 * priority entering road that has vehicles waiting.
	 * 
	 * @param vehicle: The vehicle to check availability for.
	 * @return: Boolean of if the vehicle is available or not.
	 */
	public boolean checkAvailability(Vehicle vehicle) {
		if (vehicle.getLastRoad().getEndJunction().getExitingRoads().isEmpty())
			return false;
		if (vehicle.getLastRoad().getWaitingVehicles().indexOf(vehicle) != 0)
			return false;
		else
			return true;
	}

	/**
	 * This method registers the vehicle to the junction by updating the relevant
	 * fields and printing a message.
	 * 
	 * @param vehicle: The vehicle to check in.
	 */
	// TODO: rewrite javadoc after implementation.
	public void checkIn(Vehicle vehicle) {
		vehicle.setTimeOnCurrentPart(0);
		vehicle.setCurrentRoutePart(this);
		vehicle.setStatus("- Has arrived to " + toString());
		System.out.println(vehicle.getStatus());
	}

	/**
	 * This method is called when the vehicle can leave the junction, and it updates
	 * the relevant fields and prints a message.
	 */
	public void checkOut(Vehicle vehicle) {
		if (this.canLeave(vehicle)) {
			// ((Road)vehicle.getCurrentRoutePart()).removeVehicleFromWaitingVehicles(vehicle);
			// vehicle.getCurrentRoute().getRouteParts()
			vehicle.setStatus("- has left the" + toString());
			System.out.println(vehicle.getStatus());
		} else
			stayOnCurrentPart(vehicle);
	}

	/**
	 * This method finds the next route segment that fits the given vehicle. It
	 * checks if there are enabled exiting routes for the given vehicle type and
	 * returns one of them randomly, or null if no roads are available.
	 * 
	 * @param vehicle: The given vehicle for which to find the next road segment.
	 */
	public RouteParts findNextPart(Vehicle vehicle) {
		for (Road r : exitingRoads) {
			if (r.isEnable()) {
				for (VehicleType v : r.getVehicleTypes()) {
					if (v.equals(vehicle.getVehicleType()))
						return r;
				}
			}
		}
		return null;
	}

	/**
	 * Prints a message that the vehicle has to wait in this junction this turn.
	 */
	public void stayOnCurrentPart(Vehicle vehicle) {
		if (this instanceof LightedJunction) {
			System.out.printf("- is waiting at %s for green light\n", this.toString());
		} else {
			System.out.printf("- is waiting at %s- there are previous cars on the same road.\n", this.toString());
		}
	}

	/**
	 * Returns the amount of existing objects of this class.
	 * 
	 * @return: The object count.
	 */
	public static int getObjectCounts() {
		return objectCounts;
	}

	/**
	 * Sets the amount of existing objects of this class.
	 * 
	 * @param objectCounts: The amount to be set.
	 */
	public static void setObjectCounts(int objectCounts) {
		Junction.objectCounts = objectCounts;
	}

	/**
	 * Returns the ArrayList of the entering roads to this junction.
	 * 
	 * @return: The entering roads ArrayList.
	 */
	public ArrayList<Road> getEnteringRoads() {
		return enteringRoads;
	}

	/**
	 * Sets the ArrayList of entering roads of this junction.
	 * 
	 * @param enteringRoads: The ArrayList to be set.
	 */
	public void setEnteringRoads(ArrayList<Road> enteringRoads) {
		this.enteringRoads = enteringRoads;
	}

	/**
	 * Returns the ArrayList of the exiting roads from this junction.
	 * 
	 * @return: The exiting roads ArrayList.
	 */
	public ArrayList<Road> getExitingRoads() {
		return exitingRoads;
	}

	/**
	 * Sets the exiting roads ArrayList of this junction.
	 * 
	 * @param exitingRoads: The ArrayList to be set.
	 */
	public void setExitingRoads(ArrayList<Road> exitingRoads) {
		this.exitingRoads = exitingRoads;
	}

	/**
	 * Overridden method. Returns a string representation of this object.
	 * 
	 * @return: The string representation.
	 */
	@Override
	public String toString() {
		if (this instanceof LightedJunction) {
			return String.format("Junction %s (Lighted)", this.junctionName);
		}
		return String.format("Junction %s", this.junctionName);
	}

	/**
	 * Overridden method. Returns a boolean of if this object equals the other given
	 * object.
	 * 
	 * @return: The boolean of if they equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Junction) {
			Junction other = (Junction) obj;
			if (other == null || !this.enteringRoads.equals(other.enteringRoads)
					|| !this.exitingRoads.equals(other.exitingRoads) || !this.junctionName.equals(other.junctionName)) {
				return false;
			}
			return true;
		}
		return false;
	}
}
