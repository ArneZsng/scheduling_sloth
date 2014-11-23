package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CohortDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Cohort;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CohortServiceImpl implements CohortService {
	/** The course DAO. */
	private CohortDAO cohortDAO;

	@Override
	public void saveCohort(Cohort cohort) {
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
