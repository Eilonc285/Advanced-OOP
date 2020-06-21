package utilities;

import components.Map;
import components.Moked;

public class CountryBuilder implements Builder {
	private static volatile CountryBuilder mySingleton = null;

	private CountryBuilder() {
	}

	@Override
	public Map getMap() {
		return new Map(6, false);
	}

	public static CountryBuilder getCountryBuilder() {
		CountryBuilder localRef = mySingleton;
		if (localRef == null) {
			synchronized (Moked.class) {
				localRef = mySingleton;
				if (mySingleton == null) {
					localRef = new CountryBuilder();
					mySingleton = localRef;
				}
			}
		}
		return mySingleton;
	}

	@Override
	public boolean allowedType(VehicleType type) {
		return (!type.equals(VehicleType.bicycle) && !type.equals(VehicleType.tram));
	}

}
