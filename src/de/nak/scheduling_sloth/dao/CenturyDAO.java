package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;

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
    public Century load(Long id) {
        Century century =  (Century) getSessionFactory().getCurrentSession().get(Century.class, id);
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
        Century century = (Century) getSessionFactory().getCurrentSession().get(Century.class, id);
        if (century != null) {
            List<Course> courses = century.getCourses();
            for (Course course : courses) {
                Hibernate.initialize(course.getLessons());
                for (Lesson lesson: course.getLessons()) {
                    Hibernate.initialize(lesson.getRooms());
                }
                Hibernate.initialize(course.getCentury());
                Hibernate.initialize(course.getCohort());
            }
        }
        return century;
    }
}

