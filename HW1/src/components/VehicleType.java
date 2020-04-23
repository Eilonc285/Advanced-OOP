package components;

public class VehicleType {
	private String typeName;
	private int speed;

	public VehicleType(String name, int speed) {
		this.typeName = name;
		this.speed = speed;
	}

	public String toString() {

	}

	public boolean equals(Object other) {
		if (other instanceof VehicleType) {
			return this.typeName.equals(((VehicleType) other).getTypeName())
					&& this.speed == ((VehicleType) other).getSpeed();
		}
	}

	public String getTypeName() {
		return typeName;
	}

	public int getSpeed() {
		return speed;
	}
}
