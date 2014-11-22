package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.model.*;
import de.nak.scheduling_sloth.service.CenturyService;
import de.nak.scheduling_sloth.service.CohortService;
import de.nak.scheduling_sloth.service.RoomService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by arne on 19.11.14.
 */
public class ShowRoomListAvailableAction extends ActionSupport implements Preparable {
    /** The list of rooms. */
    private List<Room> roomList = new ArrayList<Room>();
    /** Start date of the lesson. */
    private Timestamp startDate;
    /** End date of the lesson. */
    private Timestamp endDate;
    /** The passed course. */
    private Course course;
    /** The number of required seats. */
    private Integer requiredSeats = 0;
    /** The room service. */
    private RoomService roomService;
    /** The cohort service. */
    private CohortService cohortService;
    /** The century service. */
    private CenturyService centuryService;
    /** Input parameter of lesson id. */
    private Map availableRooms = new HashMap();

    @Override
    public String execute() throws Exception {
        initializeParams();


        // Check if start date is before end date
        if (!startDateBeforeEndDate()) {
            addActionError(getText("msg.startDateBeforeEndDate"));
        }
        List<Room> allRooms = roomService.loadAllRoomsWithLessons();
        for (Room room : allRooms) {
            if (room.timeSlotAvailable(startDate, endDate) && room.bigEnough(requiredSeats)) {
                roomList.add(room);
            }
        }
        return SUCCESS;
    }

    private void initializeParams() {
        for (Iterator i = availableRooms.keySet().iterator(); i.hasNext(); ) {
            String id = (String) i.next();
            Integer lessonNumber = Integer.parseInt(id);
            List<Lesson> lessons = course.getLessons();
            startDate = lessons.get(lessonNumber).getStartDate();
            endDate = lessons.get(lessonNumber).getEndDate();
        }

        java.util.Date date = new java.util.Date();
        if (startDate == null) {
            setStartDate(new Timestamp(date.getTime()));
        }
        if (endDate == null && startDate != null) {
            // Add 30 minutes to startDate
            setEndDate(new Timestamp(startDate.getTime() + (30 * 60000)));
        }

        if (course != null) {
            if (course.getCohort().getId() != null && course.getCohort().getId() != -1) {
                course.setCohort(cohortService.loadCohort(course.getCohort().getId()));
            } else if (course.getCentury().getId() != null && course.getCentury().getId() != -1) {
                course.setCentury(centuryService.loadCentury(course.getCentury().getId()));
            }
            setRequiredSeats(course.retrieveAudienceSize());
        }
    }

    @Override
    public void prepare() throws Exception {
        java.util.Date date = new java.util.Date();
        setStartDate(new Timestamp(date.getTime()));        // Add 30 minutes to startDate
        setEndDate(new Timestamp(startDate.getTime() + (30 * 60000)));
    }

    @Override
    public void validate() {
    }

    private boolean startDateBeforeEndDate() {
        if (startDate != null && endDate != null) {
            return startDate.before(endDate);
        } else {
            return false;
        }
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getRequiredSeats() {
        return requiredSeats;
    }

    public void setRequiredSeats(Integer requiredSeats) {
        this.requiredSeats = requiredSeats;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public CohortService getCohortService() {
        return cohortService;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    public CenturyService getCenturyService() {
        return centuryService;
    }

    public void setCenturyService(CenturyService centuryService) {
        this.centuryService = centuryService;
    }

    public Map getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Map availableRooms) {
        this.availableRooms = availableRooms;
    }
}
