package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.model.Course;
import org.hibernate.Hibernate;
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
        Century century =  (Century) sessionFactory.getCurrentSession().get(Century.class, id);
        if (century != null) {
            Hibernate.initialize(century.getCourses());
        }
        return century;
    }

    /**
     * Loads a single lecturer entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Century loadWithLessons(Long id) {
        Century century = (Century) sessionFactory.getCurrentSession().get(Century.class, id);
        List<Course> courses = century.getCourses();
        for (Course course : courses) {
            Hibernate.initialize(course.retrieveLessonsWithInitRooms());
            Hibernate.initialize(course.getCentury());
            Hibernate.initialize(course.getCohort());
        }
        return century;
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

