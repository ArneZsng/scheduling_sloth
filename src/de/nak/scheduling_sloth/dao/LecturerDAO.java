package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Lecturer;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Lecturer data access object.
 *
 * Created by arne on 10/28/14.
 */
public class LecturerDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the lecturer into the database.
     *
     * @param lecturer The lecturer to persist. The given entity can be transient or detached.
     */
    public void save(Lecturer lecturer) {
        sessionFactory.getCurrentSession().saveOrUpdate(lecturer);
    }

    /**
     * Loads a single lecturer entity from the database.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Lecturer load(Long id) {
        return (Lecturer) sessionFactory.getCurrentSession().get(Lecturer.class, id);
    }

    /**
     * Deletes the lecturer from the database.
     *
     * @param lecturer The lecturer to be deleted.
     */
    public void delete(Lecturer lecturer) {
        sessionFactory.getCurrentSession().delete(lecturer);
    }

    /**
     * Loads all lecturers from the database.
     *
     * @return a list or lecturer which is empty if no lecturer was found.
     */
    @SuppressWarnings("unchecked")
    public List<Lecturer> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Lecturer").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
