package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by arne on 19.11.14.
 */
public class ShowRoomListAvailableAction implements Action {
    /** The list of rooms. */
    private List<Room> roomList;
    /** Statdate of the lesson. */
    private Timestamp startDate;
    /** Enddate of the lesson. */
    private Timestamp endDate;
    /** The number of required seats. */
    private Integer requiredSeats;
    /** The room service. */
    private RoomService roomService;

    @Override
    public String execute() throws Exception {
        roomList = roomService.loadAllRooms();
        return SUCCESS;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getRequiredSeats() {
        return requiredSeats;
    }

    public void setRequiredSeats(Integer requiredSeats) {
        this.requiredSeats = requiredSeats;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
