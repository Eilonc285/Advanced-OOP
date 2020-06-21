package utilities;

import java.util.Random;

import components.Map;
import components.Road;
import components.Vehicle;

public abstract class Factory {
	public Factory() {
	}

	public abstract Vehicle getVehicle(String y);

	public static Factory getFactory(int x) {
		Random random = new Random();
		Map map = GameDriver.getDriving().getMap();
		switch (x) {
		case 2:
			return new Factory() {
				@Override
				public Vehicle getVehicle(String y) {
					String input = y.toLowerCase();
					if (input.equals("fast")) {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[3]);
					} else {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[2]);
					}
				}
			};
		case 4:
			return new Factory() {
				@Override
				public Vehicle getVehicle(String y) {
					String input = y.toLowerCase();
					if (input.equals("private")) {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[0]);
					} else if (input.equals("work")) {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[4]);
					} else {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[1]);
					}
				}
			};
		case 10:
			return new Factory() {
				@Override
				public Vehicle getVehicle(String y) {
					String input = y.toLowerCase();
					if (input.equals("public")) {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[5]);
					} else {
						Road temp = map.getRoads().get(random.nextInt(map.getRoads().size()));
						return new Vehicle(temp, temp.getVehicleTypes()[6]);
					}
				}
			};
		default:
			return new Factory() {
				@Override
				public Vehicle getVehicle(String y) {
					return null;
				}
			};
		}
	}
}
