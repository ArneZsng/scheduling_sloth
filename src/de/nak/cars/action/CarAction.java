package de.nak.cars.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.cars.model.Car;
import de.nak.cars.service.CarService;

/**
 * Action for a single car.
 *
 * @author Stephan Anft
 */
public class CarAction extends ActionSupport {
	/** Serial version UID. */
	private static final long serialVersionUID = -3393497662671380262L;

	/** The current car. */
	private Car car;

	/** The car's identifier selected by the user. */
	private Long carId;

	/** The car service. */
	private CarService carService;

	/**
	 * Saves the car to the database.
	 *
	 * @return the result string.
	 */
	public String save() {
		carService.saveCar(car);
		return SUCCESS;
	}

	/**
	 * Deletes the selected car from the database.
	 *
	 * @return the result string.
	 */
	public String delete() {
		car = carService.loadCar(carId);
		if (car != null) {
			carService.deleteCar(car);
		}
		return SUCCESS;
	}

	/**
	 * Displays the selected car in the car form.
	 *
	 * @return the result string.
	 */
	public String load() {
		car = carService.loadCar(carId);
		return SUCCESS;
	}

	/**
	 * Cancels the editing.
	 * This method is implemented in order to avoid problems with parameter submit and validation.
	 * A direct link to the "ShowCarList" action does work but results in multiple stack traces in the
	 * application's log.
	 *
	 * @return the result string.
	 */
	public String cancel() {
		return SUCCESS;
	}

	@Override
	public void validate() {
		// If the car is not set, the car ID has to be set.
		if (car == null && carId == null) {
			addActionError(getText("msg.selectCar"));
		}
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}
}
