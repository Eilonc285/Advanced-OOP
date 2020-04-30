package components;

import utilities.Utilities;

public interface RouteParts extends Utilities {
	default double calcEstimatedTime(Object obj) {
		return 0;}
	default boolean canLeave(Vehicle vehicle) {
		return false;}
	default void checkIn(Vehicle vehicle) {}
	default void checkOut(Vehicle vehicle) {}
	default RouteParts findNextPart(Vehicle vehicle) {
		return null;}
	default void stayOnCurrentPart(Vehicle vehicle) {}

}
