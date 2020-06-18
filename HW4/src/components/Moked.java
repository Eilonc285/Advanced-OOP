package components;

public class Moked {
	private static volatile Moked mySingleton = null;
	private String path = "C:\\Users\\Eilon\\Desktop";
	private int reportCounter = 0;

	private Moked() {
	}

	public static Moked getMoked() {
		Moked localRef = mySingleton;
		if (localRef == null) {
			synchronized (Moked.class) {
				localRef = mySingleton;
				if (mySingleton == null) {
					localRef = new Moked();
					mySingleton = localRef;
				}
			}
		}
		return mySingleton;
	}

	public synchronized void submitReport(int vehicleId) {
	}

	public void readReport() {
	}

	public void notifyDrivers() {
	}
}
