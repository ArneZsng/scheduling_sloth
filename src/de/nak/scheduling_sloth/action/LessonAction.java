package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.LessonService;
import de.nak.scheduling_sloth.service.RoomService;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class LessonAction extends ActionSupport implements Preparable {
    private static final long serialVersionUID = -1552918275462992805L;
    /** The current lesson. */
    private Lesson lesson;

    /** The lesson's identifier selected by the user. */
    private Long lessonId;

    /** The lesson service. */
    private LessonService lessonService;

    /** Select list of rooms. */
    private List<Room> roomList;

    /** The room service. */
    private RoomService roomService;

    /**
     * Saves the lesson to the database.
     *
     * @return the result string.
     */
    public String save() {
        lessonService.saveLesson(lesson);
        return SUCCESS;
    }



    /**
     * Displays the selected lesson in the lesson form.
     *
     * @return the result string.
     */

     public String load(){
         lesson = lessonService.loadLesson(lessonId);
         return SUCCESS;
     }

    /**
     * Start adding a Lesson
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() {
        return SUCCESS;
    }

    /**
     * Cancels the editing.
     * This method is implemented in order to avoid problems with parameter submit and validation.
     * A direct link to the "ShowLessonList" action does work but results in multiple stack traces in the
     * application's log.
     *
     * @return the result string.
     */
    public String cancel() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        // If the lesson is not set, the lesson ID has to be set.
        if (lesson == null && lessonId == null) {
            addActionError(getText("msg.selectLesson"));
        }

        // Check if lecturer is available
        if (!lesson.lecturerAvailable()) {
            addActionError(getText("msg.lecturerNotAvailable"));
        }

        // Check if rooms are available
        if (!lesson.allRoomsAvailable()) {
            addActionError(getText("msg.roomsNotAvailable"));
        }

        // Check if audience is available
        if (!lesson.audienceAvailable()) {
            addActionError(getText("msg.audienceNotAvailable"));
        }

        // Check if rooms are big enough for audience
        if (!lesson.allRoomsBigEnough()) {
            addActionError(getText("msg.roomNotBigEnough"));
        }
    }

    /**
     * Load all rooms for selection
     */
    public void prepare() {
        roomList = roomService.loadAllRooms();

        if(roomList == null) {
            roomList = new ArrayList<Room>();
        }
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    public List<Room> getRoomList() { return roomList; }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
