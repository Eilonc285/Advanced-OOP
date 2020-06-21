package utilities;

import java.util.Random;

import components.Junction;
import components.LightedJunction;

public class JFactory {

	public JFactory() {
	}

	public Junction getJunction(String x) {
		String input = x.toLowerCase();
		if (input.equals("city")) {
			return new LightedJunction();
		} else if (input.equals("country")) {
			Random rand = new Random();
			if (rand.nextBoolean()) {
				return new LightedJunction();
			} else {
				return new Junction();
			}
		} else {
			return new Junction();
		}
	}
}
