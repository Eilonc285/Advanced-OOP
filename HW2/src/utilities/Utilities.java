package utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * An interface to provide number checking / producing and message printing.
 * 
 * @author Eilon
 *
 */
public interface Utilities {

	/**
	 * Checks if the given value is between the given minimum and maximum values.
	 * 
	 * @param val: The number that should be within the range.
	 * @param min: The minimum of the valid range.
	 * @param max: The maximum of the valid range.
	 * @return: Boolean of if the value is within the range.
	 */
	default boolean checkValue(double val, double min, double max) {
		return min <= val && val <= max;
	}

	/**
	 * Prints a message about switching a faulty input with a valid one.
	 * 
	 * @param wrongVal:   The faulty input value.
	 * @param correctVal: The new valid value.
	 * @param varName:    The containing variable name.
	 */
	default void correctingMessage(double wrongVal, double correctVal, String varName) {
		System.out.printf("The value %f is illegal for%s, therefore has been replaced with %f", wrongVal, varName,
				correctVal);
	}

	/**
	 * Prints a message of an invalid input value.
	 * 
	 * @param wrongVal: The invalid value given.
	 * @param varName:  The name of the containing variable.
	 */
	default void errorMessage(double wrongVal, String varName) {
		// TODO: implement.
	}

	/**
	 * Produces a random boolean.
	 * 
	 * @return: The random boolean.
	 */
	default boolean getRandomBoolean() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	/**
	 * Produces a random integer within the given range.
	 * 
	 * @param min: The lower limit of the range.
	 * @param max: The upper limit of the range.
	 * @return: The produced random value.
	 */
	default double getRandomInt(int min, int max) {
		Random rand = new Random();
		return (rand.nextInt(max) + min + 1);
	}

	/**
	 * Produces and array of random integers within a range.
	 * 
	 * @param min:       The minimum limit of the range.
	 * @param max:       The maximum limit of the range.
	 * @param arraySize: The size of the produced array.
	 * @return: The produced array of random integers.
	 */
	default ArrayList<Integer> getRandomIntArray(int min, int max, int arraySize) {
		ArrayList arr = new ArrayList();
		for (int i = 0; i < arraySize; i++) {
			arr.add(this.getRandomInt(min, max));
		}
		return arr;
	}

	/**
	 * Prints a message of a successful creation of an object.
	 * 
	 * @param objName: The name of the created object.
	 */
	default void successMessage(String objName) {
		System.out.printf("%s has been created", objName);
	}

}
