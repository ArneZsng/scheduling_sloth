package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
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
    public Cohort load(Long id) {
        Cohort cohort =  (Cohort) getSessionFactory().getCurrentSession().get(Cohort.class, id);
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
        Cohort cohort = (Cohort) getSessionFactory().getCurrentSession().get(Cohort.class, id);
        if (cohort != null) {
            List<Course> courses = cohort.getCourses();
            for (Course course : courses) {
                Hibernate.initialize(course.getLessons());
                for (Lesson lesson: course.getLessons()) {
                    Hibernate.initialize(lesson.getRooms());
                }
                Hibernate.initialize(course.getCentury());
            }
        }
        return cohort;
    }
}

