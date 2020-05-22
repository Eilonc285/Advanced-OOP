/**
 * 
 */
package utilities;

import components.Driving;
import gui.MainFrame;

/**
 * @author Sophie Krimberg
 *
 */
public class GameDriver {

	private static int iterationTime = 100;
	private volatile static boolean running = true;
	private static Driving driving;
	private static MainFrame myFrame;

	public static MainFrame getFrame() {
		return myFrame;
	}

	public static int getIterationTime() {
		return iterationTime;
	}

	public static boolean isRunning() {
		return running;
	}

	public static Driving getDriving() {
		return driving;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		driving = new Driving(10, 20);
//		driving.drive(20);
		myFrame = new MainFrame();
		new Thread(driving).start();

	}

}
