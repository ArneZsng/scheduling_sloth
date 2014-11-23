package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;

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
    public Course load(Long id) {
        Course course = (Course) sessionFactory.getCurrentSession().get(Course.class, id);
        if(course!= null) {
            Hibernate.initialize(course.getLessons());
        }
        return course;
    }

    /**
     * Loads a single course entity from the database with its lessons and rooms.
     *
     * @param id The identifier.
     * @return a lecturer or null if no lecturer was found with the given identifier.
     */
    public Course loadWithLessonsAndRooms(Long id) {
        Course course = load(id);
        if(course != null) {
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

        }
        return course;
    }
}

