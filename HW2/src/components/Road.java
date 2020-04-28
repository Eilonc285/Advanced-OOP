package components;

import java.util.ArrayList;

import utilities.Utilities;
import utilities.VehicleType;

public class Road implements RouteParts, Utilities {
	private int[] allowedSpeedOptions;
	private boolean enable;
	private Junction startJunction;
	private Junction endJunction;
	private boolean greenlight;
	private double length;
	private int maxSpeed;
	private VehicleType[] vehicleTypes;
	private ArrayList<Vehicle> waitingVehicles;
	
	public Road (Junction start, Junction end) {}
	public void addVehicleToWaitingVehicles(Vehicle vehicle) {}
	public double calcEstimatedTime(Object obj) {}
	public double calcLength() {}
	public boolean canLeave(Vehicle vehicle) {}
	public void checkIn(Vehicle vehicle);
	public void checkout(Vehicle vehicle) {}
	public RouteParts findNextPart(Vehicle vehicle) {}
	public void removeVehicleFromWaitingVehicles(Vehicle vehicle) {}
	public void stayOnCurrentPart(Vehicle vehicle) {}
	
	
	
}
