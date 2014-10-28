package de.nak.cars.service;

import de.nak.cars.dao.CarDAO;
import de.nak.cars.model.Car;

import java.util.List;

/**
 * The car service implementation class.
 *
 * @author Stephan Anft
 */
public class CarServiceImpl implements CarService {
	/** The car DAO. */
	private CarDAO carDAO;

	@Override
	public void saveCar(Car car) {
		carDAO.save(car);
	}

	@Override
	public Car loadCar(Long id) {
		return carDAO.load(id);
	}

	@Override
	public void deleteCar(Car car) {
		carDAO.delete(car);
	}

	@Override
	public List<Car> loadAllCars() {
		return carDAO.loadAll();
	}

	public void setCarDAO(CarDAO carDAO) {
		this.carDAO = carDAO;
	}

}
