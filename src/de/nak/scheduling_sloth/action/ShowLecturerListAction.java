package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.service.LecturerService;

import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowLecturerListAction extends AbstractAction implements Action{
    /** The list of lecturers. */
    private List<Lecturer> lecturerList;

    /** The lecturer service. */
    private LecturerService lecturerService;

    @Override
    public String execute() {
        try {
            lecturerList = lecturerService.loadAllLecturers();
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }
}
