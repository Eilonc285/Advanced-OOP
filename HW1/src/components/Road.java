package components;

import java.util.ArrayList;
import java.util.Random;

import game.Driving;

public class Road {
	private Junction fromJunc;
	private Junction toJunc;
	private ArrayList<String> allowedVehicles;
	private boolean isOpen;
	private boolean isEnabled;
	private double length;
	private final int[] speedPossibilities = { 20, 40, 60, 80, 100, 120, 140 };
	private int maxSpeed;

	public Road(Junction from, Junction to) {
		this.fromJunc = from;
		this.toJunc = to;
		Random rand = new Random();
		this.isOpen = rand.nextBoolean();
		this.isEnabled = rand.nextBoolean();
		for (int i = 0; i < Driving.getTypes().length; i++) {
			if (rand.nextBoolean()) {
				this.allowedVehicles.add(Driving.getTypes()[i].getTypeName());
			}
		}
		this.length = this.countLength();
		System.out.printf("Road from %s to %s has been created\n", this.fromJunc.getJunctionName(),
				this.toJunc.getJunctionName());
		this.maxSpeed = this.speedPossibilities[rand.nextInt(this.speedPossibilities.length)];
	}

	public Road(Junction from, Junction to, ArrayList<String> allowed, boolean open, boolean enabled) {
		this.fromJunc = from;
		this.toJunc = to;
		this.allowedVehicles = allowed;
		this.isOpen = open;
		this.isEnabled = enabled;
		System.out.printf("Road from %s to %s has been created\n", this.fromJunc.getJunctionName(),
				this.toJunc.getJunctionName());

	}

	public Junction getFromJunc() {
		return fromJunc;
	}

	public void setFromJunc(Junction fromJunc) {
		this.fromJunc = fromJunc;
	}

	public Junction getToJunc() {
		return toJunc;
	}

	public void setToJunc(Junction toJunc) {
		this.toJunc = toJunc;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void addVehicleType(String type) {
		// append to allowed
	}

	public double countLength() {
		return Math.sqrt(Math.pow((this.fromJunc.getLocation().getY() - this.toJunc.getLocation().getY()), 2)
				+ Math.pow((this.fromJunc.getLocation().getX() - this.toJunc.getLocation().getX()), 2));
	}

	@Override
	public String toString() {
		return String.format("Road from %s to %s\n", this.fromJunc.getJunctionName(), this.toJunc.getJunctionName());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Road) {
			return this.fromJunc.equals(((Road) other).fromJunc) && this.toJunc.equals(((Road) other).toJunc)
					&& this.allowedVehicles.equals(((Road) other).getAllowedVehicles())
					&& this.isOpen == ((Road) other).isOpen() && this.isEnabled == ((Road) other).isEnabled()
					&& this.maxSpeed == ((Road) other).getMaxSpeed();
		}
		return false;
	}

	public ArrayList<String> getAllowedVehicles() {
		return allowedVehicles;
	}

}
