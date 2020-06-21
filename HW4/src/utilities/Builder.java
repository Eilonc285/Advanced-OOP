package utilities;

import components.Map;

public interface Builder {
	public Map getMap();

	public boolean allowedType(VehicleType type);
}
