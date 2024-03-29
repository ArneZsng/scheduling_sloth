package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.RoomDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Room;

import java.util.List;

/**
 * Service interface implementation for Room.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class RoomServiceImpl implements RoomService {
	/** The room DAO. */
	private RoomDAO roomDAO;

	@Override
	public void saveRoom(Room room) throws EntityNotSavableException {
		roomDAO.save(room);
	}

	@Override
	public Room loadRoom(Long id) throws EntityNotFoundException {
		return roomDAO.load(id);
	}

    @Override
    public Room loadRoomWithLessons(Long id) throws EntityNotFoundException {
        return roomDAO.loadWithLessons(id);
    }

	@Override
	public void deleteRoom(Room room) throws EntityNotDeletableException {
		roomDAO.delete(room);
	}

	@Override
	public List<Room> loadAllRooms() throws EntityNotFoundException {
		return roomDAO.loadAll();
	}

    @Override
    public List<Room> loadAllRoomsWithLessons() {
        return roomDAO.loadAllWithLessons();
    }

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

}
