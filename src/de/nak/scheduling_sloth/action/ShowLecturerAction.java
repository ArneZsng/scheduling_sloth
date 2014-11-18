package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Lecturer;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.LecturerService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowLecturerAction implements Action {

    /** The current lecturer. */
    private Lecturer lecturer;

    /** The lecturer's identifier selected by the user. */
    private Long lecturerId;

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

    /** The lecturer service. */
    private LecturerService lecturerService;

    /**
     * Displays the selected lecturer in the lecturer show.
     *
     * @return the result string.
     */
    @Override
    public String execute() throws Exception {
        lecturer = lecturerService.loadLecturerWithLessons(lecturerId);
        if (week == 0 || year == 0) {
            lessonList = lecturer.retrieveLessonsOfCurrentWeek();
        } else {
            lessonList = lecturer.retrieveLessonsInWeek(week, year);
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

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
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