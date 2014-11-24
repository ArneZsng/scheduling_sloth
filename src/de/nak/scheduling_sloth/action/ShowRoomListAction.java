package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;

import java.util.List;

/**
 * Show list action for Room.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class ShowRoomListAction extends AbstractAction implements Preparable {
    /** The list of rooms. */
    private List<Room> roomList;
    /** The room service. */
    private RoomService roomService;

    /**
     * Prepares load of room list by loading results.
     */
    @Override
    public void prepare() {
        try {
            roomList = roomService.loadAllRooms();
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
        }
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
