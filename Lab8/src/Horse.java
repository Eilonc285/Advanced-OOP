
public class Horse implements Runnable {

	private String id;
	private FinishingLine fLine;
	private int distance;

	public Horse(String id, FinishingLine f, int distance) {
		this.id = id;
		this.fLine = f;
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	@Override
	public void run() {
		String space = "";
		for (int i = 0; i < 10 - id.length(); i++) {
			space += " ";
		}
		while (distance > 0) {
			System.out.printf("%s:" + space + "%d meters to finish\n", id, distance);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Sleep failed");
				e.printStackTrace();
			}
			distance -= 20;
		}
		fLine.arrive(this);
	}
}
