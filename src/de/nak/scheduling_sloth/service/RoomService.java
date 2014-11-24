package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Room;

import java.util.List;

/**
 * Service interface for Room.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public interface RoomService {

	/**
	 * Creates or updates a rooms.
	 *
	 * @param room The room.
	 */
	void saveRoom(Room room) throws EntityNotSavableException;

	/**
	 * Loads a single rooms.
	 *
	 * @param id The identifier.
	 * @return a room or null.
	 */
	Room loadRoom(Long id) throws EntityNotFoundException;

    /**
     * Loads a single room with lessons and courses.
     *
     * @param id The identifier.
     * @return a lecturer or null.
     */
    Room loadRoomWithLessons(Long id) throws EntityNotFoundException;

	/**
	 * Deletes the given room.
	 *
	 * @param room The room.
	 */
	void deleteRoom(Room room)throws EntityNotDeletableException;

	/**
	 * Loads a list of all rooms.
	 *
	 * @return a list which is empty if no room was found.
	 */
	List<Room> loadAllRooms() throws EntityNotFoundException;

    /**
     * Loads a list of all rooms including lessons.
     *
     * @return a list which is empty if no room was found.
     */
    List<Room> loadAllRoomsWithLessons();

}
