package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Course;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class CourseDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the room into the database.
     *
     * @param course The course to persist. The given entity can be transient or detached.
     */

    public void save(Course course) {sessionFactory.getCurrentSession().saveOrUpdate(course);}

    /**
     * Loads a single course entity from the database.
     *
     * @param id The identifier.
     * @return a course or null if no course was found with the given identifier.
     */
    public Course load(Long id) {
        return (Course) sessionFactory.getCurrentSession().get(Course.class, id);
    }

    /**
     * Deletes the course from the database.
     *
     * @param course The room to be deleted.
     */

    public void delete(Course course) {sessionFactory.getCurrentSession().delete(course);}


    /**
     * Edits the course from the database.
     *
     * @param course The course to be deleted.
     */

    public void edit(Course course) {sessionFactory.getCurrentSession().update(course);}

    /**
     * Loads all courses from the database.
     *
     * @return a list or courses which is empty if no course was found.
     */
    @SuppressWarnings("unchecked")
    public List<Course> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Course").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

