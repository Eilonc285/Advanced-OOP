package components;

import java.util.ArrayList;
import java.util.Random;

public class Route implements RouteParts {
	private ArrayList<RouteParts> routeParts;
	private Vehicle vehicle;

	public Route(RouteParts start, Vehicle vehicle) {
		this.routeParts = new ArrayList();
		Random rand = new Random();
		this.vehicle = vehicle;
		this.routeParts.add(start);
		for (int i = 0; i < 9; i++) {
			if (this.routeParts.get(this.routeParts.size() - 1) instanceof Road) {
				this.routeParts.add(((Road) this.vehicle.getCurrentRoutePart()).getEndJunction());
			} else {
				ArrayList<Road> exRoads = ((Junction) this.routeParts.get(this.routeParts.size() - 1))
						.getExitingRoads();
				this.routeParts.add(exRoads.get(rand.nextInt(exRoads.size())));
			}
		}
	}

	@Override
	public double calcEstimatedTime(Object obj) {
		return 0;
	}

	@Override
	public boolean canLeave(Vehicle vehicle) {
		return vehicle.getCurrentRoutePart().equals(this.routeParts.get(this.routeParts.size() - 1));
	}

	@Override
	public void checkIn(Vehicle vehicle) {
		System.out.printf("%s has checked in to route from %s to %s\n", vehicle.toString(), this.routeParts.get(0),
				this.routeParts.get(this.routeParts.size() - 1));
	}

	@Override
	public void checkOut(Vehicle vehicle) {
		System.out.printf("%s has checked out from route from %s to %s\n", vehicle.toString(), this.routeParts.get(0),
				this.routeParts.get(this.routeParts.size() - 1));
	}

	@Override
	public RouteParts findNextPart(Vehicle vehicle) {
		if (this.routeParts.indexOf(vehicle.getCurrentRoutePart()) != this.routeParts.size() - 1) {
			return this.routeParts.get(this.routeParts.indexOf(vehicle.getCurrentRoutePart()) + 1);
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

}
