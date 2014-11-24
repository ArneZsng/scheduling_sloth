package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

/**
 * DAO class for Lesson.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
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
    public Lesson load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Lesson lesson = (Lesson) session.get(Lesson.class, id);
        if (lesson == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(lesson.getCourse().getLessons());
        session.getTransaction().commit();
        return lesson;
    }

    /**
     * Loads all lessons between two timestamps from the database.
     *
     * @return a list or lesson which is empty if no lesson was found.
     */
    @SuppressWarnings("unchecked")
    public List<Lesson> loadAllBetween(Timestamp startDate, Timestamp endDate) {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Lesson> lessonList = session.createQuery(
                "from Lesson as lesson where lesson.startDate <= :end and lesson.endDate > :start order by lesson.startDate asc")
                .setTimestamp("start", startDate)
                .setTimestamp("end", endDate)
                .list();
        for (Lesson lesson: lessonList) {
            Hibernate.initialize(lesson.getRooms());
        }
        session.getTransaction().commit();
        return lessonList;
    }
}

