package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Timer;
import utilities.Utilities;

/**
 * This class connects all the parts of the program and runs it.
 * 
 * @author Eilon
 *
 */
public class Driving implements Timer, Utilities {
	private Map map;
	private ArrayList<Vehicle> vehicles;
	private int drivingTime;
	private ArrayList<Timer> allTimedElements;

	/**
	 * Initializes the new Driving object with the amount of junctions and vehicles
	 * which creates a map with the given amount of junctions, and initializes all
	 * the vehicles and other variables.
	 * 
	 * @param numOfJunctions: The amount of junction objects to be created.
	 * @param numOfVehicles:  The amount of vehicle objects to be created.
	 */
	public Driving(int numOfJunctions, int numOfVehicles) {
		this.vehicles = new ArrayList<Vehicle>();
		this.allTimedElements = new ArrayList<Timer>();
		Random rand = new Random();
		this.map = new Map(numOfJunctions);
		System.out.println("================= CREATING VEHICLES =================");
//		Vehicle createdVehicle;
		for (int i = 0; i < numOfVehicles; i++) {
			Vehicle createdVehicle = new Vehicle(this.map.getRoads().get(rand.nextInt(this.map.getRoads().size())));
			this.vehicles.add(createdVehicle);
			createdVehicle.getCurrentRoute().checkIn(createdVehicle);
			createdVehicle.getLastRoad().checkIn(createdVehicle);
		}
		this.drivingTime = 0;
		for (int i = 0; i < this.vehicles.size(); i++) {
			this.allTimedElements.add(this.vehicles.get(i));
		}
		for (int i = 0; i < this.map.getJunctions().size(); i++) {
			if (this.map.getJunctions().get(i) instanceof LightedJunction) {
				this.allTimedElements.add(((LightedJunction) this.map.getJunctions().get(i)).getLights());
			}
		}
	}

	/**
	 * Returns the map containing all the junctions.
	 * 
	 * @return: The map.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Returns the vehicle array containing all the vehicle objects.
	 * 
	 * @return: vehicle ArrayList.
	 */
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * Returns the amount of time / pulses from the beginning of the program.
	 * 
	 * @return: The driving time integer.
	 */
	public int getDrivingTime() {
		return drivingTime;
	}

	/**
	 * Returns all the elements in the program that keep track of run time(implement
	 * Timer interface).
	 * 
	 * @return: The ArrayList of all the Timer elements.
	 */
	public ArrayList<Timer> getAllTimedElements() {
		return allTimedElements;
	}

	/**
	 * Sets the current map.
	 * 
	 * @param map: The new map to be set.
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Sets a new ArrayList of vehicles.
	 * 
	 * @param vehicles: The new ArrayList of vehicles.
	 */
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * Sets a new driving time integer.
	 * 
	 * @param drivingTime: The driving time integer to be set.
	 */
	public void setDrivingTime(int drivingTime) {
		this.drivingTime = drivingTime;
	}

	/**
	 * Sets a new ArrayList of all the timed elements in the program.
	 * 
	 * @param allTimedElements: The new ArrayList of all the timed elements.
	 */
	public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
		this.allTimedElements = allTimedElements;
	}

	/**
	 * Used to determine if this object is equal to another.
	 *
	 * @param other: The other object to be compared to.
	 * @return: Boolean of if the objects equal each other.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Driving) {
			return this.map.equals(((Driving) other).getMap()) && this.vehicles.equals(((Driving) other).getVehicles())
					&& this.drivingTime == ((Driving) other).getDrivingTime()
					&& this.allTimedElements.equals(((Driving) other).getAllTimedElements());
		}
		return false;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return: The String representation.
	 */
	@Override
	public String toString() {
		return String.format("Driving (%d, %d)", this.map.getJunctions().size(), this.vehicles.size());
	}

	/**
	 * Calls the incrementDrivingTime function for the given number of turns.
	 * 
	 * @param numOfTurns: The integer number of turns to call the increment
	 *                    function.
	 */
	public void drive(int numOfTurns) {
		System.out.println("================= START DRIVING=================");
		for (int i = 0; i < numOfTurns; i++) {
			System.out.printf("***************TURN %d***************\n", i);
			this.incrementDrivingTime();
			for (int j = 0; j < this.allTimedElements.size(); j++) {
				if (this.allTimedElements.get(j) instanceof TrafficLights) {
					if (((TrafficLights) this.allTimedElements.get(j)).getWorkingTime()
							% ((TrafficLights) this.allTimedElements.get(j)).getDelay() == 0) {
						((TrafficLights) this.allTimedElements.get(j)).changeLights();
					} else {
						System.out.printf("%s\n-on delay\n", ((TrafficLights) this.allTimedElements.get(j)).toString());
					}
				}
			}
		}
	}

	/**
	 * Increments the time(number of pulses) for all the timed elements in the
	 * program.
	 */
	public void incrementDrivingTime() {
		for (int i = 0; i < this.allTimedElements.size(); i++) {
			this.allTimedElements.get(i).incrementDrivingTime();
		}
	}

}
