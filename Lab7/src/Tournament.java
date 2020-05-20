import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Tournament {
	final int NUMBER_OF_PLAYERS = 8;
	private String name, file_name;
	QueueAsList playersQueue;
	ObjectOutputStream playerOut;
	ObjectInputStream playerIn;
	Player[] ar;

	public Tournament(String name, String file_name) {
		this.name = new String(name);
		this.file_name = new String(file_name);
		playersQueue = new QueueAsList();
		playerOut = null;
		playerIn = null;
		ar = new Player[NUMBER_OF_PLAYERS];

		// Fill players queue
		try {
			FileInputStream theFile = new FileInputStream(file_name);
			playerIn = new ObjectInputStream(theFile);

			System.out.println("Load from file");
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++)
				addToSortArr(ar, (Player) (playerIn.readObject()));
			playerIn.close();
			addFromArToQueue();
		} catch (IOException e) {
			if (playerIn == null) {
				addToSortArr(ar, new Player("Novak Djokovic", 10000));
				addToSortArr(ar, new Player("Andy Murray", 9000));
				addToSortArr(ar, new Player("Roger Federer", 8000));
				addToSortArr(ar, new Player("Stanislas Wawrinka", 7000));
				addToSortArr(ar, new Player("Rafael Nadal", 6000));
				addToSortArr(ar, new Player("Kei Nishikori", 5000));
				addToSortArr(ar, new Player("Tomas Berdych", 4000));
				addToSortArr(ar, new Player("David Ferrer", 3000));
				addFromArToQueue();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Deserialized class type not found");
			e.printStackTrace();
		}

		try {
			FileOutputStream theFile = new FileOutputStream(file_name, false);
			playerOut = new ObjectOutputStream(theFile);
		} catch (IOException e) {
			System.out.println("Output objects creation failed");
			e.printStackTrace();
		}
	}

	private Player simulateGame(Player first, Player second) {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			first.updateGameWin();
			first.updateTotalScore();
			return first;
		}
		second.updateGameWin();
		second.updateTotalScore();
		return second;
	}

	public void simulateTournament() {
		System.out.println("*******************************************************");
		System.out.println("Tennis tournament \"" + name + "\" is starting now");
		System.out.println("*******************************************************");
		for (int j = 0; j < Math.log10(NUMBER_OF_PLAYERS) / Math.log10(2); j++) {
			int playerAmount = playersQueue.size();
			for (int i = 0; i < playerAmount / 2; i++) {
				Player p1 = (Player) playersQueue.dequeue(), p2 = (Player) playersQueue.dequeue();
				Player winner = simulateGame(p1, p2);
				playersQueue.enqueue(winner);
			}
		}
		Player[] sorted = new Player[NUMBER_OF_PLAYERS];
		for (int i = 0; i < ar.length; i++) {
			addToSortArr(sorted, ar[i]);
		}
		ar = sorted;
		for (int i = 0; i < ar.length; i++) {
			try {
				playerOut.writeObject(ar[i]);
			} catch (IOException e) {
				System.out.println("Writing to file failed");
				e.printStackTrace();
			}
		}
	}

	private void addToSortArr(Player[] ar, Player pl) {

		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == null) {
				ar[i] = pl;
				break;
			}
		}

		Comparator<Player> cmp = new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				if (((Player) o1).getTotalScore() > ((Player) o2).getTotalScore()) {
					return 1;
				} else if (((Player) o1).getTotalScore() < ((Player) o2).getTotalScore()) {
					return -1;
				} else {
					return 0;
				}
			}
		};

		Arrays.sort(ar, cmp);
	}

	private void addFromArToQueue() {
		for (int i = 0; i < ar.length; i++) {
			playersQueue.enqueue(ar[i]);
		}
	}

	public void printPlayers() {
		System.out.println("Players ranking:");
		for (int i = ar.length - 1; i >= 0; i--)
			System.out.println((ar.length - i) + ". " + ar[i]);
	}

	public void printScores() {
		System.out.println("Players' scores:");
		for (int i = ar.length - 1; i >= 0; i--)
			System.out.printf("%s: %d\n", ar[i].getName(), ar[i].getTotalScore());
	}
}