package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CenturyService;
import de.nak.scheduling_sloth.service.CohortService;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CenturyAction extends AbstractAction implements Preparable {
    private static final long serialVersionUID = 579538793082702877L;
    /** The current century. */
    private Century century;

    /** The centuries identifier selected by the user. */
    private Long centuryId;

    /** The century service. */
    private CenturyService centuryService;

    /** Select list of cohorts. */
    private List<Cohort> cohortList;

    /** The cohort service. */
    private CohortService cohortService;

    /** The default breakTime **/
    private Integer defaultBreakTime = Century.DEFAULT_BREAKTIME;


    /**
     * Saves the century to the database.
     *
     * @return the result string.
     */
    public String save() {
        try {
            cohortList = cohortService.loadAllCohorts();
            centuryService.saveCentury(century);
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        } catch (EntityNotSavableException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    /**
     * Deletes the selected century from the database.
     *
     * @return the result string.
     */
    public String delete() {
        try {
            century = centuryService.loadCentury(centuryId);
            if (century.getCourses().isEmpty()) {
                centuryService.deleteCentury(century);
                return SUCCESS;
            } else {
                addActionError(getText("error.strong") + century.getName() + getText("error.century.delete"));
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
     * Displays the selected century in the century form.
     *
     * @return the result string.
     */
     public String load(){
         try {
             century = centuryService.loadCentury(centuryId);
             if (century.getBreakTime() != null) {
                 defaultBreakTime = century.getBreakTime();
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
     * Start adding a Century
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        // If the century is not set, the century ID has to be set.
        if (century == null && centuryId == null) {
            addActionError(getText("msg.selectCentury"));
        }
    }

    /**
     * Load all Cohorts for selection and sets the default breakTime
     */
    public void prepare() throws EntityNotFoundException {
        cohortList = cohortService.loadAllCohorts();
        if(cohortList == null) {
            cohortList = new ArrayList<Cohort>();
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

    public void setCenturyService(CenturyService centuryService) {
        this.centuryService = centuryService;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    public List<Cohort> getCohortList() {
        return this.cohortList;
    }

    public Integer getDefaultBreakTime() {
        return this.defaultBreakTime;
    }
}
