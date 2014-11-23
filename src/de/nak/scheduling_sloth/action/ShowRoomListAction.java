package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowRoomListAction extends AbstractAction implements Preparable {
    /** The list of rooms. */
    private List<Room> roomList;

    /** The room service. */
    private RoomService roomService;

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
