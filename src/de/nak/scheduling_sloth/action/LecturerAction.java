package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.service.LecturerService;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Action for a single lecturer.
 *
 * Created by arne on 10/28/14.
 */
public class LecturerAction extends AbstractAction implements Preparable {
    /** Serial version UID. */
    private static final long serialVersionUID = 6548293890662119587L;

    /** The current lecturer. */
    private Lecturer lecturer;

    /** The lecturer's identifier selected by the user. */
    private Long lecturerId;

    /** The lecturer service. */
    private LecturerService lecturerService;

    /** The default breakTime **/
    private Integer defaultBreakTime;

    /**
     * Saves the lecturer to the database.
     *
     * @return the result string.
     */
    public String save() {
        lecturerService.saveLecturer(lecturer);
        return SUCCESS;
    }

    /**
     * Deletes the selected lecturer from the database.
     *
     * @return the result string.
     */
    public String delete() {
        try {
            lecturer = lecturerService.loadLecturer(lecturerId);
            if (lecturer.getCourses().isEmpty()) {
                lecturerService.deleteLecturer(lecturer);
                return SUCCESS;
            } else {
                addActionError(getText("error.strong") + lecturer.getName() + getText("error.lecturer.delete"));
                return ERROR;
            }
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }

    }

    /**
     * Displays the selected lecturer in the lecturer form.
     *
     * @return the result string.
     */
    public String load() {
        try {
            lecturer = lecturerService.loadLecturer(lecturerId);
            if (lecturer.getBreakTime() != null) {
                defaultBreakTime = lecturer.getBreakTime();
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
     * A direct link to the "ShowLecturerList" action does work but results in multiple stack traces in the
     * application's log.
     *
     * @return the result string.
     */
    public String cancel() {
        return SUCCESS;
    }

    /**
     * Start adding a Lecturer
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        // If the lecturer is not set, the lecturer ID has to be set.
        if (lecturer == null && lecturerId == null) {
            addActionError(getText("msg.selectLecturer"));
        }
    }


    /**
     * Sets the default breakTime
     */
    public void prepare() {
        defaultBreakTime = Lecturer.DEFAULT_BREAKTIME;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerID) {
        this.lecturerId = lecturerID;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public Integer getDefaultBreakTime() {
        return this.defaultBreakTime;
    }

}
