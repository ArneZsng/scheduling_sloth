package de.nak.scheduling_sloth.action;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.RoomService;
import de.nak.scheduling_sloth.utilities.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Show action for Room.
 *
 * @author      Arne Zeising <arne.zeising@nordakademie.de>
 * @version     1.0
 * @since       2014-11-18
 */
public class ShowRoomAction extends AbstractAction {
    /** The current room. */
    private Room room;
    /** The room's identifier selected by the user. */
    private Long roomId;
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
    /** The room service. */
    private RoomService roomService;

    /**
     * Displays the selected room in the room show.
     *
     * @return the result string.
     */
    @Override
    public String execute() {
        try {
            room = roomService.loadRoomWithLessons(roomId);

            if (week == 0 || year == 0) {
                Calendar calendar = Utilities.getSchedulingCalendar();
                week = calendar.get(Calendar.WEEK_OF_YEAR);
                year = calendar.get(Calendar.YEAR);
            }

            lessonList = room.retrieveLessonsInWeek(week, year);

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
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }

    }

    /**
     * Initializes previous week.
     */
    private void initPreviousWeek() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 7);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        weekOfPreviousWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        yearOfPreviousWeek = calendar.get(Calendar.YEAR);
    }

    /**
     * Initializes next week.
     */
    private void initNextWeek() {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 7);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        weekOfNextWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        yearOfNextWeek = calendar.get(Calendar.YEAR);
    }

    /**
     * Returns default week for select.
     *
     * @return String week number as String
     */
    public String getDefaultWeek() {
        return String.format("%02d", week);
    }

    /**
     * Returns default year for select.
     *
     * @return String year number as String
     */
    public String getDefaultYear() {
        return year.toString();
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
}
