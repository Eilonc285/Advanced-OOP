package components;

import java.util.Random;

import utilities.GameDriver;

public class BigBrother {

	private static volatile BigBrother mySingleton = null;

	private BigBrother() {
	}

	public static BigBrother getBigBrother() {
		BigBrother localRef = mySingleton;
		if (localRef == null) {
			synchronized (BigBrother.class) {
				localRef = mySingleton;
				if (mySingleton == null) {
					localRef = new BigBrother();
					mySingleton = localRef;
				}
			}
		}
		return mySingleton;
	}

	public void update(Vehicle vic) {
		if (!checkLegalSpeed(vic)) {
			Moked.getMoked().submitReport(vic.getId());
			if (GameDriver.isPConsole()) {
				System.out.println("Vehicle number " + vic.getId() + " oversped");
			}
		}
	}

	private boolean checkLegalSpeed(Vehicle vic) {
		Random rand = new Random();
		double randomSpeed = (vic.getLastRoad().getLength() / vic.getTimeOnCurrentPart()) * rand.nextFloat() * 1.5f;
		return randomSpeed <= vic.getLastRoad().getMaxSpeed();
	}

}
