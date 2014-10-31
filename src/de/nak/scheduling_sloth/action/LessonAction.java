package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.LessonService;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class LessonAction extends ActionSupport {
    private static final long serialVersionUID = -1552918275462992805L;
    /** The current room. */
    private Lesson lesson;

    /** The room's identifier selected by the user. */
    private Long lessonId;

    /** The room service. */
    private LessonService lessonService;

    /**
     * Saves the room to the database.
     *
     * @return the result string.
     */
    public String save() {
        lessonService.saveLesson(lesson);
        return SUCCESS;
    }

    /**
     * Deletes the selected course from the database.
     *
     * @return the result string.
     */
    public String delete() {
        lesson = lessonService.loadLesson(lessonId);
        if (lesson != null) {
            lessonService.deleteLesson(lesson);
        }
        return SUCCESS;
    }

    /**
     * Displays the selected room in the room form.
     *
     * @return the result string.
     */

     public String load(){
         lesson = lessonService.loadLesson(lessonId);
         return SUCCESS;
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

    @Override
    public void validate() {
        // If the room is not set, the room ID has to be set.
        if (lesson == null && lessonId == null) {
            addActionError(getText("msg.selectLesson"));
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
}
