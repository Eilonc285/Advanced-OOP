package components;

import java.util.ArrayList;
import java.util.Random;

public class Route implements RouteParts {
	private ArrayList<RouteParts> routeParts;
	private Vehicle vehicle;

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
//		this.checkIn(vehicle);
	}

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

	@Override
	public boolean canLeave(Vehicle vehicle) {
		return vehicle.getCurrentRoutePart().equals(this.routeParts.get(this.routeParts.size() - 1));
	}

	@Override
	public void checkIn(Vehicle vehicle) {
		System.out.printf("- is starting a new %s\n", this.toString());
	}

	@Override
	public void checkOut(Vehicle vehicle) {
		System.out.printf("- has finished the %s. Time spent on the route: %d\n", this.toString(),
				this.vehicle.getTimeFromRouteStart());
	}

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

	@Override
	public void stayOnCurrentPart(Vehicle vehicle) {
		System.out.printf("The vehicle %s is staying on the current route part\n", vehicle.toString());
	}

	public ArrayList<RouteParts> getRouteParts() {
		return routeParts;
	}

	public void setRouteParts(ArrayList<RouteParts> routeParts) {
		this.routeParts = routeParts;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return String.format("Route from %s to %s, estimated time for route: %f.", this.routeParts.get(0).toString(),
				this.routeParts.get(this.routeParts.size() - 1).toString(), this.calcEstimatedTime(this.vehicle));
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Route) {
			return this.routeParts.equals(((Route) other).getRouteParts())
					&& this.vehicle.equals(((Route) other).getVehicle());
		}
		return false;
	}

}
