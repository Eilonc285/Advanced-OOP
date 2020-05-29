/**
 * 
 */
package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Driving;
import components.Vehicle;
import gui.MainFrame;

/**
 * @author Sophie Krimberg, Nir Barel, Eilon Cohen
 *
 */
public class GameDriver {

	private static final int iterationTime = 100;
	private volatile static boolean running = true;
	private static Driving driving;
	private static MainFrame myFrame;
	private static boolean pConsole = false;
	private static boolean pause = true;

	public static boolean getPause() {
		return pause;
	}

	public static void setPause(boolean b) {
		pause = b;
	}

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

	public static boolean isPConsole() {
		return pConsole;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myFrame = new MainFrame();
		myFrame.setStartListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				driving = new Driving(myFrame.getNumOfJunctions(), myFrame.getNumOfVehicles());
				pause = false;
				Vehicle.resetVehicleCount();
				new Thread(driving).start();
			}
		});
	}
}
