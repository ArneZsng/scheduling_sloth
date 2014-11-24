package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.RoomService;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Class for all CRUD actions on Room.
 *
 * @author      Hendrik Makait <hendrik.makait@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
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
        try {
            roomService.saveRoom(room);
            return SUCCESS;
        } catch (EntityNotSavableException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
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
        } catch (EntityNotDeletableException e) {
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

    /**
     * Validates if room is present.
     */
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
