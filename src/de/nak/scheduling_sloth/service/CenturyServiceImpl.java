package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CenturyDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Century;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CenturyServiceImpl implements CenturyService {
	/** The Century DAO. */
	private CenturyDAO centuryDAO;

	@Override
	public void saveCentury(final Century century) throws EntityNotSavableException {
		centuryDAO.save(century);
	}

    @Override
    public Century loadCentury(final Long id) throws EntityNotFoundException {
        return centuryDAO.load(id);
    }

    @Override
    public Century loadCenturyWithLessons(final Long id) throws EntityNotFoundException {
        return centuryDAO.loadWithLessons(id);
    }

	@Override
	public void deleteCentury(final Century century) throws EntityNotDeletableException {
		centuryDAO.delete(century);
	}

	@Override
	public List<Century> loadAllCenturies() throws EntityNotFoundException {
		return centuryDAO.loadAll();
	}

	public void setCenturyDAO(final CenturyDAO centuryDAO) {
		this.centuryDAO = centuryDAO;
	}

}
