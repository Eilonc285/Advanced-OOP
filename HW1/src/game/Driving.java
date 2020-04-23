package game;

import java.util.ArrayList;
import java.util.Random;

import components.Junction;
import components.Map;
import components.Road;
import components.Route;
import components.VehicleType;
import components.Vehicles;
import utilities.Point;

public class Driving {
//	private String[] types = { "car", "bus", "bicycle", "motorcycle", "truck", "tram", "jeep", "van" };
	private static final VehicleType[] types = { new VehicleType("car", 90), new VehicleType("bus", 60),
			new VehicleType("bicycle", 40), new VehicleType("motorcycle", 120), new VehicleType("truck", 80),
			new VehicleType("tram", 50), new VehicleType("jeep", 85), new VehicleType("van", 75) };

	public static VehicleType[] getTypes() {
		return types;
	}

	private int numOfJuncs;
	private int numOfVehicles;
	private Map currentMap;
	private ArrayList<Vehicles> currentVehicles;
	private double drivingTime;
	private int maxTime;

	public Driving(int juncs, int vehicles, int maxTime) {
		this.numOfJuncs = juncs;
		this.numOfVehicles = vehicles;
		this.maxTime = maxTime;
		Random rand = new Random();
//		Point temp = new Point(1, 2);
		ArrayList<Junction> jncs = new ArrayList<Junction>();
		for (int i = 0; i < numOfJuncs; i++) {
			double x = -1;
			double y = -1;
			Junction j = new Junction(String.format("Number %d", i + 1), new Point(x, y));
			jncs.add(j);
		}
		for (int i = 0; i < this.numOfVehicles; i++) {
			this.currentVehicles.add(new Vehicles(i, this.types[rand.nextInt(this.types.length)].getTypeName(),
					this.currentMap.getJunctions().get(rand.nextInt(this.currentMap.getJunctions().size()))));
		}
		this.currentMap = new Map(jncs);
//		for (int i = 0; i < maxTime; i++) {
//			System.out.printf("TURN %d\n", i + 1);
//
//			for (int k = 0; k < this.numOfVehicles; k++) {
//				ArrayList<Junction> juncList = new ArrayList();
//				ArrayList<Road> roadList = new ArrayList();
//				juncList.add(this.currentVehicles.get(k).getLastJunction());
//				for (int j = 0; j < rand.nextInt(this.numOfJuncs); j++) {
//					if (juncList.get(juncList.size() - 1).getExitingRoads().size() == 0) {
//						break;
//					}
//					int exRoadIndex = rand.nextInt(juncList.get(juncList.size() - 1).getExitingRoads().size());
//					roadList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex));
//					juncList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex).getToJunc());
//				}
////			juncList.add(this.currentMap.getJunctions().get(rand.nextInt(this.currentMap.getJunctions().size())));
//
////				juncList.add(juncList.get(j).getExitingRoads()
////						.get(rand.nextInt(juncList.get(j).getExitingRoads().size())).getToJunc());
//			}
//			if (this.currentVehicles.get(i).getCurrentRoute() == null) {
//				this.currentVehicles.get(i).setCurrentRoute(new Route(juncL));
//			}
//		}
	}

	public int getNumOfJuncs() {
		return this.numOfJuncs;
	}

	public void setNumOfJuncs(int numOfJuncs) {
		this.numOfJuncs = numOfJuncs;
	}

	public int getNumOfVehicles() {
		return this.numOfVehicles;
	}

	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}

	public Map getCurrentMap() {
		return this.currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	public ArrayList<Vehicles> getCurrentVehicles() {
		return currentVehicles;
	}

	public void setCurrentVehicles(ArrayList<Vehicles> currentVehicles) {
		this.currentVehicles = currentVehicles;
	}

	public double getDrivingTime() {
		return drivingTime;
	}

	public void setDrivingTime(double drivingTime) {
		this.drivingTime = drivingTime;
	}

	public int getMaxTime() {
		return this.maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public void addMap() {
		Random rand = new Random();
		int num = rand.nextInt(16) + 10;
		Point temp = new Point(1, 2);
		ArrayList<Junction> juncs = new ArrayList<Junction>();
		for (int i = 0; i < num; i++) {
			double x = Math.random() * 1000000;
			double y = Math.random() * 800;
			Junction j = new Junction(String.format("Number %d", i + 1), new Point(x, y));
			juncs.add(j);
		}
		this.currentMap = new Map(juncs);
	}

	public void addVehicles() {
		Random rand = new Random();
		int num = rand.nextInt(7) + 2;
		for (int i = 0; i < num; i++) {
			Vehicles v = new Vehicles(i, this.types[rand.nextInt(this.types.length)].getTypeName(),
					this.currentMap.getJunctions().get(rand.nextInt(this.currentMap.getJunctions().size())));
		}
	}

	public void startDrive(int maxTime) {
		this.maxTime = maxTime;

		ArrayList<Junction> juncList;
		ArrayList<Road> roadList;

		Random rand = new Random();

		for (int i = 0; i < maxTime; i++) {
			System.out.printf("TURN %d\n", i + 1);

			for (int k = 0; k < this.numOfVehicles; k++) {
				juncList = new ArrayList();
				roadList = new ArrayList();

				if (this.currentVehicles.get(i).getCurrentRoute() == null) {
					juncList.add(this.currentVehicles.get(k).getLastJunction());
					for (int j = 0; j < rand.nextInt(this.numOfJuncs); j++) {
						if (juncList.get(juncList.size() - 1).getExitingRoads().size() == 0) {
							break;
						}
						int exRoadIndex = rand.nextInt(juncList.get(juncList.size() - 1).getExitingRoads().size());
						roadList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex));
						juncList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex).getToJunc());
					}
					this.currentVehicles.get(i)
							.setCurrentRoute(new Route(juncList, roadList, this.currentVehicles.get(k).getType()));
				}
//			juncList.add(this.currentMap.getJunctions().get(rand.nextInt(this.currentMap.getJunctions().size())));

//				juncList.add(juncList.get(j).getExitingRoads()
//						.get(rand.nextInt(juncList.get(j).getExitingRoads().size())).getToJunc());
			}
//			if (this.currentVehicles.get(i).getCurrentRoute() == null) {
//				this.currentVehicles.get(i).setCurrentRoute(new Route(juncList, roadList, ));
//			}
		}

	}
}
