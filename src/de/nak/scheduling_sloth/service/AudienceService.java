package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.model.Audience;

import java.util.List;

/**
 * Created by hendster on 16/11/14.
 */
public interface AudienceService {

    /**
     * Creates or updates a audience.
     *
     * @param audience The audience.
     */
    void saveAudience(Audience audience);

    /**
     * Loads a single audience.
     *
     * @param id The identifier.
     * @return an audience or null.
     */
    Audience loadAudience(Long id);

    /**
     * Deletes the given audience.
     *
     * @param audience The audience.
     */
    void deleteAudience(Audience audience);

    /**
     * Loads a list of all audiences.
     *
     * @return a list which is empty if no audience was found.
     */
    List<Audience> loadAllAudiences();

}
