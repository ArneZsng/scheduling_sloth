package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.model.Room;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */

public class RoomDAO {
    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /**
     * Persists or merges the room into the database.
     *
     * @param room The room to persist. The given entity can be transient or detached.
     */
    public void save(Room room) {sessionFactory.getCurrentSession().saveOrUpdate(room);}

    /**
     * Loads a single room entity from the database.
     *
     * @param id The identifier.
     * @return a room or null if no room was found with the given identifier.
     */
    public Room load(Long id) {
        Room room =  (Room) sessionFactory.getCurrentSession().get(Room.class, id);
        if (room != null) {
            Hibernate.initialize(room.getLessons());
        }
        return room;
    }

    /**
     * Loads a single room entity from the database with its lessons and courses.
     *
     * @param id The identifier.
     * @return a room or null if no room was found with the given identifier.
     */
    public Room loadWithLessons(Long id) {
        Room room = (Room) sessionFactory.getCurrentSession().get(Room.class, id);
        if (room != null) {
            List<Lesson> lessons = room.getLessons();
            for (Lesson lesson : lessons) {
                Hibernate.initialize(lesson.getCourse().getCentury());
                Hibernate.initialize(lesson.getCourse().getCohort());
            }
        }
        return room;
    }
    
    /**
     * Deletes the room from the database.
     *
     * @param room The room to be deleted.
     */
    public void delete(Room room) {sessionFactory.getCurrentSession().delete(room);}


    /**
     * Edits the room from the database.
     *
     * @param room The room to be deleted.
     */
    public void edit(Room room) {sessionFactory.getCurrentSession().update(room);}

    /**
     * Loads all rooms from the database.
     *
     * @return a list or room which is empty if no room was found.
     */
    @SuppressWarnings("unchecked")
    public List<Room> loadAll() {
        return sessionFactory.getCurrentSession().createQuery("from Room").list();
    }

    /**
     * Loads all rooms from the database including lessons.
     *
     * @return a list or room which is empty if no room was found.
     */
    @SuppressWarnings("unchecked")
    public List<Room> loadAllWithLessons() {

        List<Room> roomList = (List<Room>) sessionFactory.getCurrentSession().createQuery("from Room").list();
        for (Room room : roomList) {
            Hibernate.initialize(room.getLessons());
        }
        return roomList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

