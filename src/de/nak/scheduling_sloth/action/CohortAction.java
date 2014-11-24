package de.nak.scheduling_sloth.action;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CohortService;


/**
 * Class for all CRUD operations on Cohort.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class CohortAction extends AbstractAction {
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
        try {
            cohortService.saveCohort(cohort);
            return SUCCESS;
        } catch (EntityNotSavableException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    /**
     * Deletes the selected cohort from the database.
     *
     * @return the result string.
     */
    public String delete() {
        try {
            cohort = cohortService.loadCohort(cohortId);
            Boolean coursesAssociated = !cohort.getCourses().isEmpty();
            Boolean centuriesAssociated = !cohort.getCenturies().isEmpty();
            if (!coursesAssociated && !centuriesAssociated) {
                cohortService.deleteCohort(cohort);
                return SUCCESS;
            } else {
                if (coursesAssociated){
                    addActionError(getText("error.strong") + cohort.getName() + getText("error.cohort.delete.courses"));
                }
                if (centuriesAssociated){
                    addActionError(getText("error.strong") + cohort.getName() +
                            getText("error.cohort.delete.centuries"));
                }
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
     * Displays the selected cohort in the cohort form.
     *
     * @return the result string.
     */

     public String load(){
         try {
             cohort = cohortService.loadCohort(cohortId);
             return SUCCESS;
         } catch (EntityNotFoundException e) {
             addActionError(getText(e.getMessage()));
             return ERROR;
         }
     }

    /**
     * Cancels the editing.
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
