package de.nak.scheduling_sloth.dao;

import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.model.Room;
import org.hibernate.Hibernate;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
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
    public Room load(Long id) {
        Room room =  (Room) getSessionFactory().getCurrentSession().get(Room.class, id);
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
        Room room = (Room) getSessionFactory().getCurrentSession().get(Room.class, id);
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
     * Loads all rooms from the database including lessons.
     *
     * @return a list or room which is empty if no room was found.
     */
    @SuppressWarnings("unchecked")
    public List<Room> loadAllWithLessons() {

        List<Room> roomList = (List<Room>) getSessionFactory().getCurrentSession().createQuery("from Room").list();
        for (Room room : roomList) {
            Hibernate.initialize(room.getLessons());
        }
        return roomList;
    }
}

