package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Cohort;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public interface CohortService {

	/**
	 * Creates or updates a cohort.
	 *
	 * @param cohort The cohort.
	 */
	void saveCohort(Cohort cohort);

	/**
	 * Loads a single cohort.
	 *
	 * @param id The identifier.
	 * @return a course or null.
	 */
     Cohort loadCohort(Long id) throws EntityNotFoundException;

    /**
     * Loads a single cohort with lessons and courses.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Cohort loadCohortWithLessons(Long id) throws EntityNotFoundException;

	/**
	 * Deletes the given cohort.
	 *
	 * @param cohort The cohort.
	 */
	void deleteCohort(Cohort cohort) throws EntityNotDeletableException;

	/**
	 * Loads a list of all cohorts.
	 *
	 * @return a list which is empty if no course was found.
	 */
	List<Cohort> loadAllCohorts() throws EntityNotFoundException;

}
