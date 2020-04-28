package utilities;

import components.Driving;

/**
 * The main class that runs the program.
 * 
 * @author Eilon
 *
 */
public class GameDriver {

	/**
	 * The main function that runs the program with 10 junctions and 20 vehicles.
	 * 
	 * @param args: main function arguments.
	 */
	public static void main(String[] args) {
		Driving driving = new Driving(10, 20);
		driving.drive(20);
	}

}
