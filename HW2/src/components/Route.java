package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Route class that generates and keeps track of the sequence of route parts
 * that the vehicle will travel through.
 * 
 * @author Nir
 *
 */
public class Route implements RouteParts {
	private ArrayList<RouteParts> routeParts;
	private Vehicle vehicle;

	/**
	 * Constructor finds a random route of 9 route parts (road and junction
	 * alternately) or until a dead end.
	 * 
	 * @param start:   The starting road of the vehicle.
	 * @param vehicle: The vehicle for which to create the route.
	 */
	public Route(RouteParts start, Vehicle vehicle) {
		this.routeParts = new ArrayList<RouteParts>();
		Random rand = new Random();
		this.vehicle = vehicle;
		this.routeParts.add(start);
		for (int i = 0; i < 9; i++) {
			if (this.routeParts.get(this.routeParts.size() - 1) instanceof Road) {
				this.routeParts.add(((Road) this.routeParts.get(this.routeParts.size() - 1)).getEndJunction());
			} else {
				if (((Junction) this.routeParts.get(this.routeParts.size() - 1)).getExitingRoads().size() > 0) {
					ArrayList<Road> exRoads = ((Junction) this.routeParts.get(this.routeParts.size() - 1))
							.getExitingRoads();
					this.routeParts.add(exRoads.get(rand.nextInt(exRoads.size())));
				} else
					break;
			}
		}
	}

	/**
	 * Overridden from RouteParts interface. Calculates the estimated time it would
	 * take the vehicle to finish the route.
	 * 
	 * @param obj: The vehicle for which to calculate the time.
	 */
	@Override
	public double calcEstimatedTime(Object obj) {
		double sum = 0;
		if (obj instanceof Vehicle) {
			for (RouteParts rp : this.routeParts) {
				sum = sum + rp.calcEstimatedTime(obj);
			}
			return sum;
		}
		return -1;
	}

	/**
	 * Overridden from RouteParts interface. Checks if the given vehicle finished
	 * the current route and can leave it.
	 * 
	 * @param vehicle: The vehicle for which to check if it can leave.
	 * @return: The boolean of if the vehicle can leave.
	 */
	@Override
	public boolean canLeave(Vehicle vehicle) {
		return vehicle.getCurrentRoutePart().equals(this.routeParts.get(this.routeParts.size() - 1));
	}

	/**
	 * Overridden from RouteParts interface. Checks in the given vehicle into this
	 * route. Prints a message.
	 * 
	 * @param vehicle: The vehicle to check in.
	 */
	@Override
	public void checkIn(Vehicle vehicle) {
		System.out.printf("- is starting a new %s\n", this.toString());
	}

	/**
	 * Overridden from RouteParts interface. Checks out the given vehicle from this
	 * route. Prints a message.
	 * 
	 * @param vehicle: The vehicle to check out.
	 */
	@Override
	public void checkOut(Vehicle vehicle) {
		System.out.printf("- has finished the %s. Time spent on the route: %d\n", this.toString(),
				this.vehicle.getTimeFromRouteStart());
	}

	/**
	 * Overridden from RouteParts interface. Finds the next route part in the route
	 * for the given vehicle and returns it. In case the vehicle has finished this
	 * route, a new route object is returned.
	 * 
	 * @param vehicle: The vehicle for which to find the next route part.
	 * @return: RoutePart type which can be a road, junction or route object.
	 */
	@Override
	public RouteParts findNextPart(Vehicle vehicle) {
//		System.out.printf("%s", vehicle.getCurrentRoutePart().getClass().toString());
		if (!this.routeParts.get(this.routeParts.size() - 1).equals(vehicle.getCurrentRoutePart())) {
//			return vehicle.getCurrentRoutePart().findNextPart(vehicle);
			RouteParts vehicleCurrentPart = vehicle.getCurrentRoutePart();
			int currentIndex = this.routeParts.indexOf(vehicleCurrentPart);
			return this.routeParts.get(currentIndex + 1);
		} else {
			if (((Junction) vehicle.getCurrentRoutePart()).getExitingRoads().size() > 0) {
				return new Route(vehicle.getLastRoad(), vehicle);
			} else {
				return new Route(this.routeParts.get(0), vehicle);
			}
		}
	}

	/**
	 * Overridden from RouteParts interface. This method is called when the vehicle
	 * can't move to the next route part. Prints a message.
	 * 
	 * @param vehicle: The vehicle that is staying on its' current route part.
	 */
	@Override
	public void stayOnCurrentPart(Vehicle vehicle) {
		System.out.printf("The vehicle %s is staying on the current route part\n", vehicle.toString());
	}

	/**
	 * Returns the route parts ArrayList of this route.
	 * 
	 * @return: route parts ArrayList.
	 */
	public ArrayList<RouteParts> getRouteParts() {
		return routeParts;
	}

	/**
	 * Sets the route parts ArrayList of this route.
	 * 
	 * @param routeParts: The ArrayList to be set.
	 */
	public void setRouteParts(ArrayList<RouteParts> routeParts) {
		this.routeParts = routeParts;
	}

	/**
	 * Returns the vehicle of this route.
	 * 
	 * @return: This route's vehicle.
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Sets the vehicle for this route.
	 * 
	 * @param vehicle: The vehicle to be set.
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The String representation.
	 */
	@Override
	public String toString() {
		return String.format("Route from %s to %s, estimated time for route: %f.", this.routeParts.get(0).toString(),
				this.routeParts.get(this.routeParts.size() - 1).toString(), this.calcEstimatedTime(this.vehicle));
	}

	/**
	 * Used to determine if this object is equal to another.
	 *
	 * @param other: The other object to be compared to.
	 * @return: Boolean of if the objects equal each other.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Route) {
			return this.routeParts.equals(((Route) other).getRouteParts())
					&& this.vehicle.equals(((Route) other).getVehicle());
		}
		return false;
	}

}
