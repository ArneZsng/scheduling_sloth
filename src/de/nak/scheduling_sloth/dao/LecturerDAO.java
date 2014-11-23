package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Lecturer data access object.
 *
 * Created by arne on 10/28/14.
 */
public class LecturerDAO extends AbstractDAO<Lecturer> {

    public LecturerDAO() {
        super(Lecturer.class.getSimpleName());
    }
    /**
     * Loads a single lecturer entity from the database.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Lecturer load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Lecturer lecturer =  (Lecturer) session.get(Lecturer.class, id);
        if (lecturer == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(lecturer.getCourses());
        session.getTransaction().commit();
        return lecturer;
    }

    /**
     * Loads a single lecturer entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Lecturer loadWithLessons(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Lecturer lecturer =  (Lecturer) session.get(Lecturer.class, id);
        if (lecturer == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        List<Course> courses = lecturer.getCourses();
        for (Course course : courses) {
            Hibernate.initialize(course.getLessons());
            for (Lesson lesson: course.getLessons()) {
                Hibernate.initialize(lesson.getRooms());
            }
            Hibernate.initialize(course.getCentury());
            Hibernate.initialize(course.getCohort());

        }
        session.getTransaction().commit();
        return lecturer;
    }
}
