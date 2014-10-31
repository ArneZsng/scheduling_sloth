package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CohortService;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CohortAction extends ActionSupport {
    private static final long serialVersionUID = -4754341871128447493L;
    /** The current cohort. */
    private Cohort cohort;

    /** The cohort identifier selected by the user. */
    private Long cohortId;

    /** The cohort service. */
    private CohortService cohortService;

    /**
     * Saves the cohort to the database.
     *
     * @return the result string.
     */
    public String save() {
        cohortService.saveCohort(cohort);
        return SUCCESS;
    }

    /**
     * Deletes the selected cohort from the database.
     *
     * @return the result string.
     */
    public String delete() {
        cohort = cohortService.loadCohort(cohortId);
        if (cohortId != null) {
            cohortService.deleteCohort(cohort);
        }
        return SUCCESS;
    }

    /**
     * Displays the selected cohort in the cohort form.
     *
     * @return the result string.
     */

     public String load(){
         cohort = cohortService.loadCohort(cohortId);
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
        // If the cohort is not set, the cohort ID has to be set.
        if (cohort == null && cohortId == null) {
            addActionError(getText("msg.selectCohort"));
        }
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public Long getCohortId() {
        return cohortId;
    }

    public void setCohortId(Long cohortId) {
        this.cohortId = cohortId;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
    }
}
