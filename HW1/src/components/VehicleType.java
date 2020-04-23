package components;

public class VehicleType {
	private String typeName;
	private int speed;

	public VehicleType(String name, int speed) {
		this.typeName = name;
		this.speed = speed;
	}

	@Override
	public String toString() {
//		implement
		return String.format("toString VehicleType\n");
	}

	public boolean equals(Object other) {
		if (other instanceof VehicleType) {
			return this.typeName.equals(((VehicleType) other).getTypeName())
					&& this.speed == ((VehicleType) other).getSpeed();
		}
		return false;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getSpeed() {
		return speed;
	}
}
