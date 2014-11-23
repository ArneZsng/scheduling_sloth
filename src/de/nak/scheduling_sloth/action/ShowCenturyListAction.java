package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Century;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.service.CenturyService;
import de.nak.scheduling_sloth.service.CohortService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowCenturyListAction extends AbstractAction implements Preparable {
    /** The list of centuries. */
    private List<Century> centuryList;

    /** The century service. */
    private CenturyService centuryService;

    /** Select list of cohorts. */
    private List<Cohort> cohortList;

    /** The cohort service. */
    private CohortService cohortService;

    @Override
    public void prepare() {
        try {
            centuryList = centuryService.loadAllCenturies();
            cohortList = cohortService.loadAllCohorts();
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
        }
    }

    public List<Century> getCenturyList() {
        return centuryList;
    }

    public void setCenturyService(CenturyService centuryService) {
        this.centuryService = centuryService;
    }

    public List<Cohort> getCohortList() { return cohortList; }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
    }
}
