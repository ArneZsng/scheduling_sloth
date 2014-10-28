package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CenturyDAO;
import de.nak.scheduling_sloth.dao.CohortDAO;
import de.nak.scheduling_sloth.model.Century;
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
    public Cohort loadCohort(Long id) {
        return cohortDAO.load(id);
    }

	@Override
	public void deleteCohort(Cohort cohort) {
		cohortDAO.delete(cohort);
	}

	@Override
	public List<Cohort> loadAllCohorts() {
		return cohortDAO.loadAll();
	}

	public void setCohortDAO(CohortDAO cohortDAO) {
		this.cohortDAO = cohortDAO;
	}

}
