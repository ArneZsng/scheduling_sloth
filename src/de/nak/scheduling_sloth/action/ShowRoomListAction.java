package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowRoomListAction extends AbstractAction implements Action {
    /** The list of rooms. */
    private List<Room> roomList;

    /** The room service. */
    private RoomService roomService;

    @Override
    public String execute() {
        try {
            roomList = roomService.loadAllRooms();
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
