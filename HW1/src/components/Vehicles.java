package components;

public class Vehicles {
	private int id;
	private String type;
	private int speed;
	private Route currentRoute;
	private Junction lastJunction;
	private Road lastRoad;
	private boolean movesNow;
	private double spentTime;

	public Vehicles(int id, String type, Junction lastJunction) {
		this.id = id;
		this.type = type;
		this.lastJunction = lastJunction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	public Junction getLastJunction() {
		return lastJunction;
	}

	public void setLastJunction(Junction lastJunction) {
		this.lastJunction = lastJunction;
	}

	public Road getLastRoad() {
		return lastRoad;
	}

	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}

	public boolean isMovesNow() {
		return movesNow;
	}

	public void setMovesNow(boolean movesNow) {
		this.movesNow = movesNow;
	}

	public double getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(double spentTime) {
		this.spentTime = spentTime;
	}

	public void move() {
	}

	public void status() {
	}

	public void checkIn() {
	}

}
