package de.nak.cars.service;

import de.nak.cars.model.Car;

import java.util.List;

/**
 * Car service interface.
 *
 * @author Stephan Anft
 */
public interface CarService {

	/**
	 * Creates or updates a car.
	 *
	 * @param car The car.
	 */
	void saveCar(Car car);

	/**
	 * Loads a single car.
	 *
	 * @param id The identifier.
	 * @return a car or null.
	 */
	Car loadCar(Long id);

	/**
	 * Deletes the given car.
	 *
	 * @param car The car.
	 */
	void deleteCar(Car car);

	/**
	 * Loads a list of all cars.
	 *
	 * @return a list which is empty if no car was found.
	 */
	List<Car> loadAllCars();

}
