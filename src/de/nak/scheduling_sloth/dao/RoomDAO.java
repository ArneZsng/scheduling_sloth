package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.model.Room;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO class for Room.
 *
 * @author      Arne Zeising <arne.zeising@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class RoomDAO extends AbstractDAO<Room> {

    public RoomDAO() {
        super(Room.class.getSimpleName());
    }

    /**
     * Loads a single room entity from the database.
     *
     * @param id The identifier.
     * @return a room or null if no room was found with the given identifier.
     */
    public Room load(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Room room =  (Room) session.get(Room.class, id);
        if (room == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        Hibernate.initialize(room.getLessons());
        session.getTransaction().commit();
        return room;
    }

    /**
     * Loads a single room entity from the database with its lessons and courses.
     *
     * @param id The identifier.
     * @return a room or null if no room was found with the given identifier.
     */
    public Room loadWithLessons(Long id) throws EntityNotFoundException {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Room room = (Room) session.get(Room.class, id);
        if (room == null) {
            session.getTransaction().rollback();
            throw new EntityNotFoundException(EntityNotFoundException.DEFAULT_MESSAGE);
        }
        List<Lesson> lessons = room.getLessons();
        for (Lesson lesson : lessons) {
            Hibernate.initialize(lesson.getCourse().getCentury());
            Hibernate.initialize(lesson.getCourse().getCohort());
        }
        session.getTransaction().commit();
        return room;
    }

    /**
     * Loads all rooms from the database including lessons.
     *
     * @return a list or room which is empty if no room was found.
     */
    @SuppressWarnings("unchecked")
    public List<Room> loadAllWithLessons() {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> roomList = (List<Room>) session.createQuery("from Room").list();
        for (Room room : roomList) {
            Hibernate.initialize(room.getLessons());
        }
        session.getTransaction().commit();
        return roomList;
    }
}

