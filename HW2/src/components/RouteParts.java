package components;

import utilities.Utilities;

/**
 * Interface for helper methods for vehicle registration.
 * 
 * @author Nir
 *
 */
public interface RouteParts extends Utilities {
	/**
	 * Calculate the estimated time it would take the given vehicle to pass this
	 * route part.
	 * 
	 * @param obj: The given vehicle.
	 * @return: The time it would take.
	 */
	double calcEstimatedTime(Object obj);

	/**
	 * Checks if the given vehicle can leave this route part.
	 * 
	 * @param vehicle: The vehicle for which to check.
	 * @return: The boolean of if the vehicle can leave.
	 */
	boolean canLeave(Vehicle vehicle);

	/**
	 * Checks in the given vehicle into this route part, updates the relevant fields
	 * and prints a message.
	 * 
	 * @param vehicle: The vehicle to check in.
	 */
	void checkIn(Vehicle vehicle);

	/**
	 * Checks out the vehicle of this current route part, updates the relevant
	 * fields and prints a message.
	 * 
	 * @param vehicle: The vehicle to check out.
	 */
	void checkOut(Vehicle vehicle);

	/**
	 * Finds the next part on the route for this vehicle after this current route
	 * part.
	 * 
	 * @param vehicle: The vehicle for which to find the next part,
	 * @return: The next route part.
	 */
	RouteParts findNextPart(Vehicle vehicle);

	/**
	 * This method will be called in case the vehicle can proceed to the next route
	 * part in the current turn. Prints out a message.
	 * 
	 * @param vehicle: The vehicle which can't proceed.
	 */
	void stayOnCurrentPart(Vehicle vehicle);

}
