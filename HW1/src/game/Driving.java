package game;

import java.util.ArrayList;
import java.util.Random;

import components.Junction;
import components.Map;
import components.VehicleType;
import components.Vehicles;
import utilities.Point;

public class Driving {
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
//		Point temp = new Point(1, 2);
		ArrayList<Junction> jncs = new ArrayList<Junction>();
		for (int i = 0; i < numOfJuncs; i++) {
			double x = Math.random() * 1000000;
			double y = Math.random() * 800;
			Junction j = new Junction(String.format("Number %d", i + 1), new Point(x, y));
			jncs.add(j);
		}
		this.currentMap = new Map(jncs);
	}

	public int getNumOfJuncs() {
		return numOfJuncs;
	}

	public void setNumOfJuncs(int numOfJuncs) {
		this.numOfJuncs = numOfJuncs;
	}

	public int getNumOfVehicles() {
		return numOfVehicles;
	}

	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}

	public Map getCurrentMap() {
		return currentMap;
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
		return maxTime;
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
		for(int i = 0; i < num; i++) {
			Vehicles v = new Vehicles(i, String.format("Type %d", i + 1), this.currentMap.)
			
		}
	}
}
