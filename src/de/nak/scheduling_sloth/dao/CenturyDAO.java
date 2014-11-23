package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class CenturyDAO extends AbstractDAO<Century> {

    public CenturyDAO() {
        super(Century.class.getSimpleName());
    }

    /**
     * Loads a single century entity from the database.
     *
     * @param id The identifier.
     * @return a century or null if no century was found with the given identifier.
     */
    public Century load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Century century =  (Century) session.get(Century.class, id);
        if (century == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(century.getCourses());
        session.getTransaction().commit();
        return century;
    }

    /**
     * Loads a single lecturer entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Century loadWithLessons(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Century century = (Century) session.get(Century.class, id);
        if (century == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        List<Course> courses = century.getCourses();
        for (Course course : courses) {
            Hibernate.initialize(course.getLessons());
            for (Lesson lesson: course.getLessons()) {
                Hibernate.initialize(lesson.getRooms());
            }
            Hibernate.initialize(course.getCentury());
            Hibernate.initialize(course.getCohort());
        }
        session.getTransaction().commit();
        return century;
    }
}

