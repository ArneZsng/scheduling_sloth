package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.AudienceDAO;
import de.nak.scheduling_sloth.model.Audience;

import java.util.List;

/**
 * Created by hendster on 16/11/14.
 */
public class AudienceServiceImpl implements AudienceService{
    /** The audience DAO. */
    private AudienceDAO audienceDAO;

    @Override
    public void saveAudience(Audience audience) {
        audienceDAO.save(audience);
    }

    @Override
    public Audience loadAudience(Long id) {
        return audienceDAO.load(id);
    }

    @Override
    public void deleteAudience(Audience audience) {
        audienceDAO.delete(audience);
    }

    @Override
    public List<Audience> loadAllAudiences() {
        return audienceDAO.loadAll();
    }

    public void setAudienceDAO(AudienceDAO cohortDAO) {
        this.audienceDAO = cohortDAO;
    }

}
