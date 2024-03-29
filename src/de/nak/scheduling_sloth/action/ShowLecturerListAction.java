package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.service.LecturerService;
import java.util.List;

/**
 * Show list action for Lecturer.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class ShowLecturerListAction extends AbstractAction implements Preparable{
    /** The list of lecturers. */
    private List<Lecturer> lecturerList;
    /** The lecturer service. */
    private LecturerService lecturerService;

    /**
     * Prepares load of lecturer list by loading results.
     */
    @Override
    public void prepare() {
        try {
            lecturerList = lecturerService.loadAllLecturers();
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
        }
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }
}
