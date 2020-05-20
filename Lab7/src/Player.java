import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int totalScore;
	private transient int tournamentScore;

	public Player(String name, int total) {
		this.name = name;
		tournamentScore = 0;
		this.totalScore = total;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void updateTotalScore() {
		totalScore += tournamentScore;
	}

	public void updateGameWin() {
		tournamentScore += 10;
	}

	public String toString() {
		return String.format("Player name: %s. Total score: %d", name, totalScore);
	}

	public String getName() {
		return name;
	}
}