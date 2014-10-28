package de.nak.scheduling_sloth.action;


import com.opensymphony.xwork2.ActionSupport;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.service.LecturerService;

/**
 * Action for a single lecturer.
 *
 * Created by arne on 10/28/14.
 */
public class LecturerAction extends ActionSupport {
    /** Serial version UID. */
    private static final long serialVersionUID = 6548293890662119587L;

    /** The current lecturer. */
    private Lecturer lecturer;

    /** The lecturer's identifier selected by the user. */
    private Long lecturerId;

    /** The lecturer service. */
    private LecturerService lecturerService;

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
        lecturer = lecturerService.loadLecturer(lecturerId);
        if (lecturer != null) {
            lecturerService.deleteLecturer(lecturer);
        }
        return SUCCESS;
    }

    /**
     * Displays the selected lecturer in the lecturer form.
     *
     * @return the result string.
     */
    public String load() {
        lecturer = lecturerService.loadLecturer(lecturerId);
        return SUCCESS;
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

    @Override
    public void validate() {
        // If the lecturer is not set, the lecturer ID has to be set.
        if (lecturer == null && lecturerId == null) {
            addActionError(getText("msg.selectLecturer"));
        }
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

}
