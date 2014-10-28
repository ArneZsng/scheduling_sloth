package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.RoomDAO;
import de.nak.scheduling_sloth.model.Room;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class RoomServiceImpl implements RoomService {
	/** The room DAO. */
	private RoomDAO roomDAO;

	@Override
	public void saveRoom(Room room) {
		roomDAO.save(room);
	}

	@Override
	public Room loadRoom(Long id) {
		return roomDAO.load(id);
	}

	@Override
	public void deleteRoom(Room room) {
		roomDAO.delete(room);
	}

	@Override
	public List<Room> loadAllRooms() {
		return roomDAO.loadAll();
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

}
