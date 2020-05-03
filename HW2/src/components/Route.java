package components;

import java.util.ArrayList;
import java.util.Random;

public class Route implements RouteParts {
	private ArrayList<RouteParts> routeParts;
	private Vehicle vehicle;

	public Route(RouteParts start, Vehicle vehicle) {
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

	public double calcEstimatedTime(Object obj) {
		return 0;
	}

	public boolean canLeave(Vehicle vehicle) {
		return false;
	}

	public void checkIn(Vehicle vehicle) {
	}

	public void checkout(Vehicle vehicle) {
	}

	public RouteParts findNextPart(Vehicle vehicle) {
		return null;
	}

	public void stayOnCurrentPart(Vehicle vehicle) {
	}

}
