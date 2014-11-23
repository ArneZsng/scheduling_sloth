package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class RoomAction extends AbstractAction implements Preparable{
    private static final long serialVersionUID = 6411696302084986323L;
    /** The current room. */
    private Room room;

    /** The room's identifier selected by the user. */
    private Long roomId;

    /** The room service. */
    private RoomService roomService;

    /** The default breakTime **/
    private Integer defaultBreakTime;

    /**
     * Saves the room to the database.
     *
     * @return the result string.
     */
    public String save() {
        roomService.saveRoom(room);
        return SUCCESS;
    }

    /**
     * Deletes the selected room from the database.
     *
     * @return the result string.
     */
    public String delete() {
        try {
            room = roomService.loadRoom(roomId);
            if (room.getLessons().isEmpty()) {
                roomService.deleteRoom(room);
                return SUCCESS;
            } else {
                addActionError(getText("error.strong") + room.getName() + getText("error.room.delete"));
                return ERROR;
            }
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }


    }

    /**
     * Displays the selected room in the room form.
     *
     * @return the result string.
     */
    public String load() {
        try {
            room = roomService.loadRoom(roomId);
            if (room.getBreakTime() != null) {
                defaultBreakTime = room.getBreakTime();
            }
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    /**
     * Cancels the editing.
     * This method is implemented in order to avoid problems with parameter submit and validation.
     * A direct link to the "ShowRoomList" action does work but results in multiple stack traces in the
     * application's log.
     *
     * @return the result string.
     */
    public String cancel() {
        return SUCCESS;
    }

    /**
     * Start adding a Room
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() {
        return SUCCESS;
    }


    @Override
    public void validate() {
        // If the room is not set, the room ID has to be set.
        if (room == null && roomId == null) {
            addActionError(getText("msg.selectRoom"));
        }
    }

    /**
     * Sets the default breakTime
     */
    public void prepare() {
        defaultBreakTime = Room.DEFAULT_BREAKTIME;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Integer getDefaultBreakTime() {
        return this.defaultBreakTime;
    }

}
