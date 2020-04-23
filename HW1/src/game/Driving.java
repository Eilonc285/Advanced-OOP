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
	private int maxNumberOfDrives;

	public Driving(int juncs, int vehicles, int maxTime) {
		this.numOfJuncs = juncs;
		this.numOfVehicles = vehicles;
		this.maxTime = maxTime;
		this.maxNumberOfDrives = 9;
		Random rand = new Random();

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

			ArrayList<Junction> juncList = new ArrayList();
			ArrayList<Road> roadList = new ArrayList();

			juncList.add(this.currentVehicles.get(i).getLastJunction());
			for (int j = 0; j < this.maxNumberOfDrives; j++) {
				if (juncList.get(juncList.size() - 1).getExitingRoads().size() == 0) {
					break;
				}
				int exRoadIndex = rand.nextInt(juncList.get(juncList.size() - 1).getExitingRoads().size());
				roadList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex));
				juncList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex).getToJunc());
			}
			this.currentVehicles.get(i)
					.setCurrentRoute(new Route(juncList, roadList, this.currentVehicles.get(i).getType()));
		}
		this.currentMap = new Map(jncs);
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

//		ArrayList<Junction> juncList;
//		ArrayList<Road> roadList;

		Random rand = new Random();

		for (int i = 0; i < maxTime; i++) {
			System.out.printf("TURN %d\n", i + 1);

//			for (int k = 0; k < this.numOfVehicles; k++) {
//				juncList = new ArrayList();
//				roadList = new ArrayList();
//
//				if (this.currentVehicles.get(i).getCurrentRoute() == null) {
//					juncList.add(this.currentVehicles.get(k).getLastJunction());
//					for (int j = 0; j < this.maxNumberOfDrives; j++) {
//						if (juncList.get(juncList.size() - 1).getExitingRoads().size() == 0) {
//							break;
//						}
//						int exRoadIndex = rand.nextInt(juncList.get(juncList.size() - 1).getExitingRoads().size());
//						roadList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex));
//						juncList.add(juncList.get(juncList.size() - 1).getExitingRoads().get(exRoadIndex).getToJunc());
//					}
//					this.currentVehicles.get(i)
//							.setCurrentRoute(new Route(juncList, roadList, this.currentVehicles.get(k).getType()));
//				}
//			}
			for (int car = 0; car < this.numOfVehicles; car++) {
				Vehicles currentCar = this.currentVehicles.get(car);
				if (currentCar.getLastJunction().equals(currentCar.getCurrentRoute().getStart())) {
					System.out.printf(
							"%s, average speed: %d, ID: %d is starting route from Junction %s to Junction %s\n",
							currentCar.getType(), currentCar.getSpeed(), currentCar.getId(),
							currentCar.getCurrentRoute().getStart(), currentCar.getCurrentRoute().getEnd());
				}
				if (currentCar.getLastJunction().equals(currentCar.getCurrentRoute().getEnd())) {
					System.out.printf("%s, average speed: %d, ID: %d stays at Junction %s - no exiting roads.\n",
							currentCar.getType(), currentCar.getSpeed(), currentCar.getId(),
							currentCar.getCurrentRoute().getEnd());
				} else if (currentCar.getLastJunction().isHasLights()) {
					int lastJuncIndx = currentCar.getCurrentRoute().getJunctions()
							.indexOf(currentCar.getLastJunction());
					if (currentCar.getLastJunction().isHasLights()) {
						if (currentCar.getCurrentRoute().getRoads().get(lastJuncIndx).isOpen()) {
							System.out.printf("%s, average speed: %d, ID: %d has left Junction %s\n",
									currentCar.getType(), currentCar.getSpeed(), currentCar.getId(),
									currentCar.getLastJunction().getJunctionName());
							System.out.printf("%s, average speed: %d, ID: %d is moving on Road from %s to %s\n",
									currentCar.getType(), currentCar.getSpeed(), currentCar.getId(),
									currentCar.getCurrentRoute().getStart(), currentCar.getCurrentRoute().getEnd());
						}
					}
				} else if (currentCar.getLastJunction().checkAvailability(currentCar.getLastRoad())) {

				}
			}
		}
	}
}
