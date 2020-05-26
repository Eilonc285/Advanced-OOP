
public class HorsesRace {

	public static void main(String[] args) {

		FinishingLine finishingLine = new FinishingLine();
		int startingDistance = 500;

		Horse h1 = new Horse("One", finishingLine, startingDistance);
		Horse h2 = new Horse("Two", finishingLine, startingDistance);
		Horse h3 = new Horse("Three", finishingLine, startingDistance);
		Horse h4 = new Horse("Four", finishingLine, startingDistance);
		Horse h5 = new Horse("Five", finishingLine, startingDistance);
		Horse h6 = new Horse("Six", finishingLine, startingDistance);
		Horse h7 = new Horse("Seven", finishingLine, startingDistance);
		Horse h8 = new Horse("Eight", finishingLine, startingDistance);
		Horse h9 = new Horse("Nine", finishingLine, startingDistance);
		Horse h10 = new Horse("Ten", finishingLine, startingDistance);

		Thread[] horses = { new Thread(h1), new Thread(h2), new Thread(h3), new Thread(h4), new Thread(h5),
				new Thread(h6), new Thread(h7), new Thread(h8), new Thread(h9), new Thread(h10) };

		for (int i = 0; i < horses.length; i++) {
			horses[i].start();
		}
	}

}
