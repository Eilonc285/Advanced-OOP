package components;

import java.util.ArrayList;
import java.util.Random;

public class RandomTrafficLights extends TrafficLights {
	public RandomTrafficLights(ArrayList<Road> roads) {
		super(roads);
	}

	public void changeIndex() {
		if (this.getRoads().size() != 0) {
			Random rand = new Random();
			this.setGreenLightIndex(rand.nextInt(this.getRoads().size()));
		}
	}
}
