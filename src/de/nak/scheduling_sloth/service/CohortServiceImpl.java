package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CohortDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Cohort;

import java.util.List;

/**
 * Service interface implementation for Cohort.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class CohortServiceImpl implements CohortService {
	/** The course DAO. */
	private CohortDAO cohortDAO;

	@Override
	public void saveCohort(Cohort cohort) throws EntityNotSavableException {
		cohortDAO.save(cohort);
	}

    @Override
    public Cohort loadCohort(Long id) throws EntityNotFoundException {
        return cohortDAO.load(id);
    }

    @Override
    public Cohort loadCohortWithLessons(Long id) throws EntityNotFoundException {
        return cohortDAO.loadWithLessons(id);
    }

    @Override
	public void deleteCohort(Cohort cohort)throws EntityNotDeletableException {
		cohortDAO.delete(cohort);
	}

	@Override
	public List<Cohort> loadAllCohorts() throws EntityNotFoundException {
		return cohortDAO.loadAll();
	}

	public void setCohortDAO(CohortDAO cohortDAO) {
		this.cohortDAO = cohortDAO;
	}

}
