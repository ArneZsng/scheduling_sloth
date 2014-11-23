package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
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
    public Lecturer load(Long id) {
        Lecturer lecturer =  (Lecturer) sessionFactory.getCurrentSession().get(Lecturer.class, id);
        if (lecturer != null) {
            Hibernate.initialize(lecturer.getCourses());
        }
        return lecturer;
    }

    /**
     * Loads a single lecturer entity from the database with its courses and lessons.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Lecturer loadWithLessons(Long id) {
        Lecturer lecturer = (Lecturer) sessionFactory.getCurrentSession().get(Lecturer.class, id);
        if (lecturer != null) {
            List<Course> courses = lecturer.getCourses();
            for (Course course : courses) {
                Hibernate.initialize(course.getLessons());
                for (Lesson lesson: course.getLessons()) {
                    Hibernate.initialize(lesson.getRooms());
                }
                Hibernate.initialize(course.getCentury());
                Hibernate.initialize(course.getCohort());
            }
        }
        return lecturer;
    }
}
