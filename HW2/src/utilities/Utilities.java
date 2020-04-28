package utilities;

import java.util.ArrayList;
import java.util.Random;

public interface Utilities {

	default boolean checkValue(double val, double min, double max) {
		return min <= val && val <= max;
	}

	default void correctingMessage(double wrongVal, double correctVal, String varName) {
		System.out.printf("The value %f is illegal for%s, therefore has been replaced with %f", wrongVal, varName,
				correctVal);
	}

	default void errorMessage(double wrongVal, String varName) {

	}

	default boolean getRandomBoolean() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	default double getRandomInt(int min, int max) {
		Random rand = new Random();
		return (rand.nextInt(max) + min + 1);
	}

	default ArrayList<Integer> getRandomIntArray(int min, int max, int arraySize) {
		ArrayList arr = new ArrayList();
		for (int i = 0; i < arraySize; i++) {
			arr.add(this.getRandomInt(min, max));
		}
		return arr;
	}

	default void successMessage(String objName) {
		System.out.printf("%s has been created", objName);
	}

}
