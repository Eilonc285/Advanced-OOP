package components;

import java.util.ArrayList;

public class SequentialTrafficLights extends TrafficLights {
	private int increment;

	public SequentialTrafficLights(ArrayList<Road> roads) {
		super(roads);
	}

	public void changeIndex() {
		this.setGreenLightIndex((this.getGreenLightIndex() + 1) % this.getRoads().size());
	}
}
