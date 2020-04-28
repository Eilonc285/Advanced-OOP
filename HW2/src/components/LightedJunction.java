package components;

public class LightedJunction extends Junction {
	private TrafficLights lights;
	
	public LightedJunction(){
		super();
		
	}
	public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {}
	
	public double calcEstimatedTime(Object obj) {
		return 0;}
	public boolean canLeave(Vehicle vehicle) {
		return false;}
	

}
