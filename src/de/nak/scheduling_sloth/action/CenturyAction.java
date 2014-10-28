package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.service.CenturyService;
import de.nak.scheduling_sloth.service.CourseService;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CenturyAction extends ActionSupport {
    /** The current century. */
    private Century century;

    /** The centuries identifier selected by the user. */
    private Long centuryId;

    /** The century service. */
    private CenturyService centuryService;

    /**
     * Saves the century to the database.
     *
     * @return the result string.
     */
    public String save() {
        centuryService.saveCentury(century);
        return SUCCESS;
    }

    /**
     * Deletes the selected century from the database.
     *
     * @return the result string.
     */
    public String delete() {
        century = centuryService.loadCentury(centuryId);
        if (century != null) {
            centuryService.deleteCentury(century);
        }
        return SUCCESS;
    }

    /**
     * Displays the selected century in the century form.
     *
     * @return the result string.
     */

     public String load(){
         century = centuryService.loadCentury(centuryId);
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
        // If the century is not set, the century ID has to be set.
        if (century == null && centuryId == null) {
            addActionError(getText("msg.selectCentury"));
        }
    }

    public Century getCentury() {
        return century;
    }

    public void setCentury(Century century) {
        this.century = century;
    }

    public Long getCenturyId() {
        return centuryId;
    }

    public void setCenturyId(Long centuryId) {
        this.centuryId = centuryId;
    }

    public void setCourseService(CourseService courseService) {
        this.centuryService = centuryService;
    }
}
