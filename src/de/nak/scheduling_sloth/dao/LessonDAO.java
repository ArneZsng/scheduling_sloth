package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class LessonDAO extends AbstractDAO<Lesson> {

    public LessonDAO() {
        super(Lesson.class.getSimpleName());
    }

    /**
     * Loads a single lesson entity from the database.
     *
     * @param id The identifier.
     * @return a lesson or null if no lesson was found with the given identifier.
     */
    public Lesson load(Long id) {
        Lesson lesson = (Lesson) getSessionFactory().getCurrentSession().get(Lesson.class, id);
        if (lesson != null) {
            Hibernate.initialize(lesson.getCourse().getLessons());
        }
        return lesson;
    }

    /**
     * Loads all lessons between two timestamps from the database.
     *
     * @return a list or lesson which is empty if no lesson was found.
     */
    @SuppressWarnings("unchecked")
    public List<Lesson> loadAllBetween(Timestamp startDate, Timestamp endDate) {
        List<Lesson> lessonList = getSessionFactory().getCurrentSession().createQuery(
                "from Lesson as lesson where lesson.startDate <= :end and lesson.endDate > :start order by lesson.startDate asc")
                .setTimestamp("start", startDate)
                .setTimestamp("end", endDate)
                .list();

        for (Lesson lesson: lessonList) {
            Hibernate.initialize(lesson.getRooms());
        }
        return lessonList;
    }
}

