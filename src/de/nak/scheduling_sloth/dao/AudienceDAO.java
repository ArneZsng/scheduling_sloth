package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Audience;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by hendster on 16/11/14.
 */
public class AudienceDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the audience into the database.
     *
     * @param audience The audience to persist. The given entity can be transient or detached.
     */

    public void save(Audience audience) {sessionFactory.getCurrentSession().saveOrUpdate(audience);}

    /**
     * Loads a single cohort entity from the database.
     *
     * @param id The identifier.
     * @return an audience or null if no audience was found with the given identifier.
     */
    public Audience load(Long id) {
        return (Audience) sessionFactory.getCurrentSession().get(Audience.class, id);
    }

    /**
     * Deletes the audience from the database.
     *
     * @param audience The audience to be deleted.
     */

    public void delete(Audience audience) {sessionFactory.getCurrentSession().delete(audience);}


    /**
     * Edits the audiences from the database.
     *
     * @param audience The audience to be deleted.
     */

    public void edit(Audience audience) {sessionFactory.getCurrentSession().update(audience);}

    /**
     * Loads all audiences from the database.
     *
     * @return a list of audiences which is empty if no audience was found.
     */
    @SuppressWarnings("unchecked")
    public List<Audience> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Audience").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
