package de.nak.scheduling_sloth.action;

import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.service.LecturerService;

import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowLecturerListAction {
    /** The list of lecturers. */
    private List<Lecturer> lecturerList;

    /** The lecturer service. */
    private LecturerService lecturerService;

    @Override
    public String execute() throws Exception {
        lecturerList = lecturerService.loadAllLecturers();
        return SUCCESS;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }
}
