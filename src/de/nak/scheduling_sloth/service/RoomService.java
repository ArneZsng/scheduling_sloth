package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.model.Room;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public interface RoomService {

	/**
	 * Creates or updates a rooms.
	 *
	 * @param room The room.
	 */
	void saveRoom(Room room);

	/**
	 * Loads a single rooms.
	 *
	 * @param id The identifier.
	 * @return a room or null.
	 */
	Room loadRoom(Long id);

	/**
	 * Deletes the given room.
	 *
	 * @param room The room.
	 */
	void deleteRoom(Room room);

	/**
	 * Loads a list of all rooms.
	 *
	 * @return a list which is empty if no room was found.
	 */
	List<Room> loadAllRooms();

}
