package de.nak.scheduling_sloth.service;

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
	void saveCentury(Century century);

	/**
	 * Loads a single century.
	 *
	 * @param id The identifier.
	 * @return a century or null.
	 */
     Century loadCentury(Long id);

	/**
	 * Deletes the given century.
	 *
	 * @param century The century.
	 */
	void deleteCentury(Century century);

	/**
	 * Loads a list of all centuries.
	 *
	 * @return a list which is empty if no course was found.
	 */
	List<Century> loadAllCenturies();

}
