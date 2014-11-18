package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.CohortService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowCohortAction implements Action {

    /** The current cohort. */
    private Cohort cohort;

    /** The cohort's identifier selected by the user. */
    private Long cohortId;

    /** The passed calender week. */
    private Integer week = 0;

    /** The passed year. */
    private Integer year = 0;

    /** The list to populate week select. */
    private List<String> weeks = new ArrayList<String>();

    /** The list to populate year select. */
    private List<String> years = new ArrayList<String>();

    /** The passed lesson list. */
    private List<Lesson> lessonList;

    /** The cohort service. */
    private CohortService cohortService;

    /**
     * Displays the selected cohort in the cohort show.
     *
     * @return the result string.
     */
    @Override
    public String execute() throws Exception {
        cohort = cohortService.loadCohortWithLessons(cohortId);
        if (week == 0 || year == 0) {
            lessonList = cohort.retrieveLessonsOfCurrentWeek();
        } else {
            lessonList = cohort.retrieveLessonsInWeek(week, year);
        }

        // Populate calendar weeks for select box
        for (int i = 1; i < 54; i++) {
            weeks.add(String.format("%02d", i));
        }

        // Populate years for select box, starting 2 years ago
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int j = -2; j < 10; j++) {
            years.add(Integer.toString(currentYear + j));
        }

        return SUCCESS;
    }

    public String getDefaultWeek() {
        if (week == 0) {
            Calendar calendar = Calendar.getInstance();
            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
            return String.format("%02d", currentWeek);
        } else {
            return week.toString();
        }
    }

    public String getDefaultYear() {
        if (year == 0) {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            return Integer.toString(currentYear);
        } else {
            return year.toString();
        }
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<String> weeks) {
        this.weeks = weeks;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
