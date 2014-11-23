package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Century;
import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public interface CenturyService {

	/**
	 * Creates or updates a century.
	 *
	 * @param century The century.
	 */
	void saveCentury(Century century) throws EntityNotSavableException;

	/**
	 * Loads a single century.
	 *
	 * @param id The identifier.
	 * @return a century or null.
	 */
     Century loadCentury(Long id) throws EntityNotFoundException;

    /**
     * Loads a single century with lessons and courses.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Century loadCenturyWithLessons(Long id) throws EntityNotFoundException;

	/**
	 * Deletes the given century.
	 *
	 * @param century The century.
	 */
	void deleteCentury(Century century) throws EntityNotDeletableException;

	/**
	 * Loads a list of all centuries.
	 *
	 * @return a list which is empty if no course was found.
	 */
	List<Century> loadAllCenturies() throws EntityNotFoundException;

}
