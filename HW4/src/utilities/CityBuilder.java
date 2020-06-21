package utilities;

import components.Map;
import components.Moked;

public class CityBuilder implements Builder {
	private static volatile CityBuilder mySingleton = null;

	private CityBuilder() {
	}

	@Override
	public Map getMap() {
		return new Map(12, true);
	}

	public static CityBuilder getCityBuilder() {
		CityBuilder localRef = mySingleton;
		if (localRef == null) {
			synchronized (Moked.class) {
				localRef = mySingleton;
				if (mySingleton == null) {
					localRef = new CityBuilder();
					mySingleton = localRef;
				}
			}
		}
		return mySingleton;
	}

	@Override
	public boolean allowedType(VehicleType type) {
		return (!type.equals(VehicleType.truck) && !type.equals(VehicleType.semitrailer));
	}

}
