package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.RoomService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowRoomAction implements Action {

    /** The current room. */
    private Room room;

    /** The room's identifier selected by the user. */
    private Long roomId;

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

    /** The room service. */
    private RoomService roomService;

    /**
     * Displays the selected room in the room show.
     *
     * @return the result string.
     */
    @Override
    public String execute() throws Exception {
        room = roomService.loadRoomWithLessons(roomId);
        if (week == 0 || year == 0) {
            lessonList = room.retrieveLessonsOfCurrentWeek();
        } else {
            lessonList = room.retrieveLessonsInWeek(week, year);
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
