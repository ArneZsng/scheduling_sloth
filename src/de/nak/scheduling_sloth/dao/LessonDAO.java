package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Lesson;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class LessonDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the lesson into the database.
     *
     * @param lesson The lesson to persist. The given entity can be transient or detached.
     */

    public void save(Lesson lesson) {sessionFactory.getCurrentSession().saveOrUpdate(lesson);}

    /**
     * Loads a single lesson entity from the database.
     *
     * @param id The identifier.
     * @return a lesson or null if no lesson was found with the given identifier.
     */
    public Lesson load(Long id) {
        return (Lesson) sessionFactory.getCurrentSession().get(Lesson.class, id);
    }

    /**
     * Deletes the lessons from the database.
     *
     * @param lesson The lesson to be deleted.
     */

    public void delete(Lesson lesson) {sessionFactory.getCurrentSession().delete(lesson);}


    /**
     * Edits the lesson from the database.
     *
     * @param lesson The lesson to be deleted.
     */

    public void edit(Lesson lesson) {sessionFactory.getCurrentSession().update(lesson);}

    /**
     * Loads all lessons from the database.
     *
     * @return a list or lesson which is empty if no lesson was found.
     */
    @SuppressWarnings("unchecked")
    public List<Lesson> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Lesson").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

