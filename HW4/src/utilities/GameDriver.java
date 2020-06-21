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
	private static boolean userMessages = false;
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

	public static boolean isUserMessages() {
		return userMessages;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myFrame = new MainFrame();
		myFrame.setStartListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				running = false; // kill previous threads
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					running = true;
				}
				driving = new Driving(myFrame.getNumOfJunctions(), myFrame.getNumOfVehicles());
				pause = false;
				Vehicle.resetVehicleCount();
				new Thread(driving).start();
			}
		});
		myFrame.setBuildListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				running = false; // kill previous threads
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					running = true;
				}
				int choice = myFrame.builderDialog();
				Builder builder;
				if (choice == 0) {
					builder = CityBuilder.getCityBuilder();
				} else {
					builder = CountryBuilder.getCountryBuilder();
				}
				driving = new Driving(myFrame.getNumOfJunctions(), myFrame.getNumOfVehicles(), builder);
				pause = false;
				Vehicle.resetVehicleCount();
				new Thread(driving).start();
			}
		});
	}

}
