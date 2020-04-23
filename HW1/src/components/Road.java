package components;

import java.util.ArrayList;
import java.util.Random;

public class Road {
	private Junction fromJunc;
	private Junction toJunc;
	private ArrayList<String> allowedVehicles;
	private boolean isOpen;
	private boolean isEnabled;
	private double length;
	private int maxSpeed;

	public Road(Junction from, Junction to) {
		this.fromJunc = from;
		this.toJunc = to;
		Random rand = new Random();
		this.isOpen = rand.nextBoolean();
		this.isEnabled = rand.nextBoolean();
		// set allowed vehicles
		this.length = this.countLength();
	}

	public Road(Junction from, Junction to, ArrayList<String> allowed, boolean open, boolean enabled) {
		this.fromJunc = from;
		this.toJunc = to;
		this.allowedVehicles = allowed;
		this.isOpen = open;
		this.isEnabled = enabled;
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
		// implement
	}

	@Override
	public boolean equals(Object other) {
		// implement
	}

}
