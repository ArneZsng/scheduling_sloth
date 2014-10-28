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
	public void saveCentury(Century century) {
		centuryDAO.save(century);
	}

    @Override
    public Century loadCentury(Long id) {
        return centuryDAO.load(id);
    }

	@Override
	public void deleteCentury(Century century) {
		centuryDAO.delete(century);
	}

	@Override
	public List<Century> loadAllCenturies() {
		return centuryDAO.loadAll();
	}

	public void setCenturyDAO(CenturyDAO centuryDAO) {
		this.centuryDAO = centuryDAO;
	}

}
