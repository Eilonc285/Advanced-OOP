package utilities;

import java.util.Random;

import components.Map;
import components.Road;
import components.Vehicle;

public abstract class Prototype {
	public static Vehicle cloneVehicle(Vehicle vehicle) {
		Random random = new Random();
		Map map = GameDriver.getDriving().getMap();
		Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
		return new Vehicle(temp, vehicle.getVehicleType());
	}
}
