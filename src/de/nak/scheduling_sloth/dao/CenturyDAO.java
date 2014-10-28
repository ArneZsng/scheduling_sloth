package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Century;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class CenturyDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the century into the database.
     *
     * @param century The century to persist. The given entity can be transient or detached.
     */

    public void save(Century century) {sessionFactory.getCurrentSession().saveOrUpdate(century);}

    /**
     * Loads a single century entity from the database.
     *
     * @param id The identifier.
     * @return a century or null if no century was found with the given identifier.
     */
    public Century load(Long id) {
        return (Century) sessionFactory.getCurrentSession().get(Century.class, id);
    }

    /**
     * Deletes the century from the database.
     *
     * @param century The century to be deleted.
     */

    public void delete(Century century) {sessionFactory.getCurrentSession().delete(century);}


    /**
     * Edits the century from the database.
     *
     * @param century The century to be deleted.
     */

    public void edit(Century century) {sessionFactory.getCurrentSession().update(century);}

    /**
     * Loads all centuries from the database.
     *
     * @return a list or centuries which is empty if no room was found.
     */
    @SuppressWarnings("unchecked")
    public List<Century> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Century").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

