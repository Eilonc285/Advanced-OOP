/**
 * 
 */
package utilities;

import components.Driving;

/**
 * @author Sophie Krimberg
 *
 */
public class GameDriver {

	public static int iterationTime = 200;
	public volatile static boolean running = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Driving driving = new Driving(10, 20);
//		driving.drive(20);
		new Thread(driving).start();

	}

}
