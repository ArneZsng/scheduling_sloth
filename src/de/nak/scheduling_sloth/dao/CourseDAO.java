package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class CourseDAO extends AbstractDAO<Course> {

    public CourseDAO() {
        super(Course.class.getSimpleName());
    }

    /**
     * Loads a single course entity from the database.
     *
     * @param id The identifier.
     * @return a course or null if no course was found with the given identifier.
     */
    public Course load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Course course = (Course) session.get(Course.class, id);
        if (course == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(course.getLessons());
        session.getTransaction().commit();
        return course;
    }

    /**
     * Loads a single course entity from the database with its lessons and rooms.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Course loadWithLessonsAndRooms(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Course course = (Course) session.get(Course.class, id);
        for (Lesson lesson: course.getLessons()) {
            Hibernate.initialize(lesson.getRooms());
        }
        if (course.getCohort() != null) {
            Hibernate.initialize(course.getCohort().getCourses());
            Hibernate.initialize(course.getCohort().getCenturies());
        }
        if (course.getCentury() != null) {
            Hibernate.initialize(course.getCentury().getCourses());
        }
        session.getTransaction().commit();
        return course;
    }
}

