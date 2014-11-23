package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CohortService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowCohortListAction extends AbstractAction implements Action {
    /** The list of cohorts. */
    private List<Cohort> cohortList;

    /** The cohort service. */
    private CohortService cohortService;

    @Override
    public String execute() {
        try {
            cohortList = cohortService.loadAllCohorts();
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    public List<Cohort> getCohortList() {
        return cohortList;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;}
}
