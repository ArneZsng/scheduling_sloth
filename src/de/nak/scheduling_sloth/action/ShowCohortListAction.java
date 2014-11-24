package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CohortService;

import java.util.List;

/**
 * Show list action for Cohort.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class ShowCohortListAction extends AbstractAction implements Preparable {
    /** The list of cohorts. */
    private List<Cohort> cohortList;
    /** The cohort service. */
    private CohortService cohortService;

    /**
     * Prepares load of cohort list by loading results.
     */
    @Override
    public void prepare() {
        try {
            cohortList = cohortService.loadAllCohorts();
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
        }
    }

    public List<Cohort> getCohortList() {
        return cohortList;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;}
}
