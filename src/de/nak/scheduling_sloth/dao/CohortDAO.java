package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Course;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class CohortDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the cohort into the database.
     *
     * @param cohort The cohort to persist. The given entity can be transient or detached.
     */

    public void save(Cohort cohort) {sessionFactory.getCurrentSession().saveOrUpdate(cohort);}

    /**
     * Loads a single cohort entity from the database.
     *
     * @param id The identifier.
     * @return a cohort or null if no cohort was found with the given identifier.
     */
    public Cohort load(Long id) {
        Cohort cohort =  (Cohort) sessionFactory.getCurrentSession().get(Cohort.class, id);
        if (cohort != null) {
            Hibernate.initialize(cohort.getCourses());
            Hibernate.initialize(cohort.getCenturies());
        }
        return cohort;
    }

    /**
     * Loads a single lecturer entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Cohort loadWithLessons(Long id) {
        Cohort cohort = (Cohort) sessionFactory.getCurrentSession().get(Cohort.class, id);
        List<Course> courses = cohort.getCourses();
        for (Course course : courses) {
            Hibernate.initialize(course.retrieveLessonsWithInitRooms());
            Hibernate.initialize(course.getCohort());
            Hibernate.initialize(course.getCohort());
        }
        return cohort;
    }

    /**
     * Deletes the cohort from the database.
     *
     * @param cohort The cohort to be deleted.
     */

    public void delete(Cohort cohort) {sessionFactory.getCurrentSession().delete(cohort);}

    /**
     * Edits the cohorts from the database.
     *
     * @param cohort The cohort to be deleted.
     */

    public void edit(Cohort cohort) {sessionFactory.getCurrentSession().update(cohort);}

    /**
     * Loads all cohorts from the database.
     *
     * @return a list or cohorts which is empty if no cohort was found.
     */
    @SuppressWarnings("unchecked")
    public List<Cohort> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Cohort").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

