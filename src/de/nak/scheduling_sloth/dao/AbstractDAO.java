package de.nak.scheduling_sloth.dao;

import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by kevinscholz on 23/11/14.
 */
public abstract class AbstractDAO<E> {
    /** The Hibernate session factory. */
    protected SessionFactory sessionFactory;
    protected String table;

    public AbstractDAO(String table) {
        this.table = table;
    }

    /**
     * Persists or merges the object to save into the database.
     *
     * @param E The object to persist. The given entity can be transient or detached.
     */
    public void save(E objectToSave) {
        sessionFactory.getCurrentSession().saveOrUpdate(objectToSave);
    }

    /**
     * Deletes the object from the database.
     *
     * @param E The object to be deleted.
     */
    public void delete(E objectToDelete) {
        sessionFactory.getCurrentSession().delete(objectToDelete);
    }

    /**
     * Edits the object from the database.
     *
     * @param E The object to be edited.
     */
    public void edit(E objectToEdit) {
        sessionFactory.getCurrentSession().update(objectToEdit);
    }

    /**
     * Loads all objects from the database.
     *
     * @return a list which is empty if no element was found.
     */
    @SuppressWarnings("unchecked")
    public List<E> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + table).list();
    }

    /**
     * Loads a single entity from the database.
     *
     * @param id The identifier.
     * @return an Instance of E or null if no element was found with the given identifier.
     */
    public abstract E load(Long id);

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
