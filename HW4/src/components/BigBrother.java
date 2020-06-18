package components;

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
//			Moked.getMoked().submitReport(vic.getId());
			System.out.println("" + vic.getId() + " oversped");
		}
	}

	private boolean checkLegalSpeed(Vehicle vic) {
		return vic.getLastRoad().getLength() / vic.getTimeOnCurrentPart() <= vic.getLastRoad().getMaxSpeed();
	}

}
