package components;

import java.util.ArrayList;

public class Route implements RouteParts {
	private ArrayList<RouteParts> RouteParts;
	private Vehicle vehicle;
	
	public Route(RouteParts start, Vehicle vehicle) {}
	public double calcEstimatedTime(Object obj) {}
	public boolean canLeave(Vehicle vehicle) {}
	public void checkIn(Vehicle vehicle) {}
	public void checkout(Vehicle vehicle) {}
	public RouteParts findNextPart(Vehicle vehicle) {}
	public void stayOnCurrentPart(Vehicle vehicle) {}
	
}
