package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO class for Cohort.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class CohortDAO extends AbstractDAO<Cohort> {

    public CohortDAO() {
        super(Cohort.class.getSimpleName());
    }

    /**
     * Loads a single cohort entity from the database.
     *
     * @param id The identifier.
     * @return a cohort or null if no cohort was found with the given identifier.
     */
    public Cohort load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cohort cohort =  (Cohort) session.get(Cohort.class, id);
        if (cohort == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(cohort.getCourses());
        Hibernate.initialize(cohort.getCenturies());
        session.getTransaction().commit();
        return cohort;
    }

    /**
     * Loads a single cohort entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a cohort or null if no lecturer was found with the given identifier.
     */
    public Cohort loadWithLessons(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cohort cohort = (Cohort) session.get(Cohort.class, id);
        if (cohort == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        List<Course> courses = cohort.getCourses();
        for (Course course : courses) {
            Hibernate.initialize(course.getLessons());
            for (Lesson lesson: course.getLessons()) {
                Hibernate.initialize(lesson.getRooms());
            }
            Hibernate.initialize(course.getCentury());
        }
        session.getTransaction().commit();
        return cohort;
    }
}

