package components;

import java.util.ArrayList;

public class Route {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private double delay;
	private String vehicleType;

	public Route(ArrayList<Junction> junc, ArrayList<Road> roads, String vehType) {
		this.junctions = junc;
		this.roads = roads;
		this.vehicleType = vehType;
	}

	public Route(Junction start, Junction end, String vehType) {

	}

	public Junction getStart() {
		return this.junctions.get(0);
	}

	public Junction getEnd() {
		return this.junctions.get(this.junctions.size() - 1);
	}
}
