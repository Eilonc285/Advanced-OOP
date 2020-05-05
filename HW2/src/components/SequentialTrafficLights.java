package components;

import java.util.ArrayList;

public class SequentialTrafficLights extends TrafficLights {
	private int increment;

	public SequentialTrafficLights(ArrayList<Road> roads) {
		super(roads);
		this.increment = 1;
	}

	public void changeIndex() {
		if (this.getRoads().size() == 0) {
			this.setGreenLightIndex((this.getGreenLightIndex() + this.increment));
		} else {
			this.setGreenLightIndex((this.getGreenLightIndex() + this.increment) % this.getRoads().size());
		}
	}
}
