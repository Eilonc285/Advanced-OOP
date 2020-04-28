package components;

import java.util.ArrayList;

public class Route implements RouteParts {
	private ArrayList<RouteParts> RouteParts;
	private Vehicle vehicle;
	
	public Route(RouteParts start, Vehicle vehicle) {}
	public double calcEstimatedTime(Object obj) {
		return 0;}
	public boolean canLeave(Vehicle vehicle) {
		return false;}
	public void checkIn(Vehicle vehicle) {}
	public void checkout(Vehicle vehicle) {}
	public RouteParts findNextPart(Vehicle vehicle) {
		return null;}
	public void stayOnCurrentPart(Vehicle vehicle) {}
	
}
