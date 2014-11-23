package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CenturyDAO;
import de.nak.scheduling_sloth.model.Century;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CenturyServiceImpl implements CenturyService {
	/** The Century DAO. */
	private CenturyDAO centuryDAO;

	@Override
	public void saveCentury(final Century century) {
		centuryDAO.save(century);
	}

    @Override
    public Century loadCentury(final Long id) {
        return centuryDAO.load(id);
    }

    @Override
    public Century loadCenturyWithLessons(final Long id) {
        return centuryDAO.loadWithLessons(id);
    }

	@Override
	public void deleteCentury(final Century century) {
		centuryDAO.delete(century);
	}

	@Override
	public List<Century> loadAllCenturies() {
		return centuryDAO.loadAll();
	}

	public void setCenturyDAO(final CenturyDAO centuryDAO) {
		this.centuryDAO = centuryDAO;
	}

}
