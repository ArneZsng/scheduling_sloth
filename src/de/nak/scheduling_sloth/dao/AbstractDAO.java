package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by kevinscholz on 23/11/14.
 */
public abstract class AbstractDAO<E> {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;
    /** The table name */
    private String table;

    public AbstractDAO(final String table) {
        this.table = table;
    }

    /**
     * Persists or merges the object to save into the database.
     *
     * @param objectToSave The object to persist. The given entity can be transient or detached.
     */
    public final void save(final E objectToSave) {
        sessionFactory.getCurrentSession().saveOrUpdate(objectToSave);
    }

    /**
     * Deletes the object from the database.
     *
     * @param objectToDelete The object to be deleted.
     */
    public final void delete(final E objectToDelete) throws EntityNotDeletableException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (objectToDelete == null) {
            session.getTransaction().rollback();
            throw new EntityNotDeletableException(EntityNotDeletableException.DEFAULT_MESSAGE);
        }
        session.delete(objectToDelete);
        session.getTransaction().commit();
    }

    /**
     * Edits the object from the database.
     *
     * @param objectToEdit The object to be edited.
     */
    public final void edit(final E objectToEdit) {
        sessionFactory.getCurrentSession().update(objectToEdit);
    }

    /**
     * Loads all objects from the database.
     *
     * @return a list which is empty if no element was found.
     */
    @SuppressWarnings("unchecked")
    public final List<E> loadAll() throws EntityNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<E> result = session.createQuery("from " + table).list();
        if (result == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        session.getTransaction().commit();
        return result;
    }

    /**
     * Loads a single entity from the database.
     *
     * @param id The identifier.
     * @return an Instance of E or null if no element was found with the given identifier.
     */
    public abstract E load(Long id) throws EntityNotFoundException;

    public final void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public final String getTable() {
        return table;
    }
    public final void setTable(final String table) {
        this.table = table;
    }
}
