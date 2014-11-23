package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.LessonService;
import de.nak.scheduling_sloth.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowScheduleAction extends AbstractAction implements Action {
    /** The passed calendar week. */
    private Integer week = 0;
    /** The passed year. */
    private Integer year = 0;
    /** The previous calendar week. */
    private Integer weekOfPreviousWeek = 0;
    /** The year of previous calendar week. */
    private Integer yearOfPreviousWeek = 0;
    /** The previous calendar week. */
    private Integer weekOfNextWeek = 0;
    /** The year of previous calendar week. */
    private Integer yearOfNextWeek = 0;
    /** The list to populate week select. */
    private List<String> weeks = new ArrayList<String>();
    /** The list to populate year select. */
    private List<String> years = new ArrayList<String>();
    /** The passed lesson list. */
    private List<Lesson> lessonList;
    /** The lesson service. */
    private LessonService lessonService;

    /**
     * Displays the selected cohort in the cohort show.
     *
     * @return the result string.
     */
    @Override
    public String execute() {
        if (week == 0 || year == 0) {
            Calendar calendar = Utilities.getSchedulingCalendar();
            week = calendar.get(Calendar.WEEK_OF_YEAR);
            year = calendar.get(Calendar.YEAR);
        }

        lessonList = lessonService.loadAllLessonsInWeek(week, year);

        initPreviousWeek();
        initNextWeek();

        // Populate calendar weeks for select box
        for (int i = 1; i < 54; i++) {
            weeks.add(String.format("%02d", i));
        }

        // Populate years for select box, starting 2 years ago
        Calendar calendar = Utilities.getSchedulingCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int j = -2; j < 10; j++) {
            years.add(Integer.toString(currentYear + j));
        }

        return SUCCESS;
    }

    private void initPreviousWeek() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 7);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        weekOfPreviousWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        yearOfPreviousWeek = calendar.get(Calendar.YEAR);
    }

    private void initNextWeek() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 7);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        weekOfNextWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        yearOfNextWeek = calendar.get(Calendar.YEAR);
    }

    public String getDefaultWeek() {
        return String.format("%02d", week);
    }

    public String getDefaultYear() {
        return year.toString();
    }

    public String getStartDate() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public String getEndDate() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(calendar.getTime());
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

    public Integer getWeekOfPreviousWeek() {
        return weekOfPreviousWeek;
    }

    public void setWeekOfPreviousWeek(Integer weekOfPreviousWeek) {
        this.weekOfPreviousWeek = weekOfPreviousWeek;
    }

    public Integer getYearOfPreviousWeek() {
        return yearOfPreviousWeek;
    }

    public void setYearOfPreviousWeek(Integer yearOfPreviousWeek) {
        this.yearOfPreviousWeek = yearOfPreviousWeek;
    }

    public Integer getWeekOfNextWeek() {
        return weekOfNextWeek;
    }

    public void setWeekOfNextWeek(Integer weekOfNextWeek) {
        this.weekOfNextWeek = weekOfNextWeek;
    }

    public Integer getYearOfNextWeek() {
        return yearOfNextWeek;
    }

    public void setYearOfNextWeek(Integer yearOfNextWeek) {
        this.yearOfNextWeek = yearOfNextWeek;
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

    public LessonService getLessonService() {
        return lessonService;
    }

    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }
}
