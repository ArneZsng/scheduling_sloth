package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.model.*;
import de.nak.scheduling_sloth.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CourseAction extends ActionSupport implements Preparable{
    private static final long serialVersionUID = -2085704409810512813L;
    /** The current course. */
    private Course course;
    /** The current lessons. */
    private List<Lesson> lessons = new ArrayList<Lesson>();
    /** Start date of the first lesson. */
    private Timestamp startDate;
    /** End date of the first lesson. */
    private Timestamp endDate;
    /** The list of the passed rooms. */
    private String[] rooms;
    /** The list of the selected rooms. */
    private List<Long> selectedRooms = new ArrayList<Long>();
    /** The current number of repetitions. */
    private Integer numberOfRepetitions;
    /** The course's identifier selected by the user. */
    private Long courseId;
    private Long courseLessonId;
    /** The course service. */
    private CourseService courseService;
    /** Select list of cohorts. */
    private List<Cohort> cohortList = new ArrayList<Cohort>();
    /** The cohort service. */
    private CohortService cohortService;
    /** Select list of centuries. */
    private List<Century> centuryList = new ArrayList<Century>();
    /** The century service. */
    private CenturyService centuryService;
    /** Select list of lecturer. */
    private List<Lecturer> lecturerList = new ArrayList<Lecturer>();
    /** The lecturer service. */
    private LecturerService lecturerService;
    /** Select list of room. */
    private List<Room> roomList = new ArrayList<Room>();
    /** The lecturer service. */
    private RoomService roomService;
    /** The lesson service. */
    private LessonService lessonService;

    String REDIRECT = "redirect";


    /**
     * Saves the course to the database.
     *
     * @return the result string.
     */
    @SkipValidation
    public String save() {
        courseService.saveCourse(course);

        for (Lesson lesson : course.getLessons()) {
            lesson.setCourse(course);

            // TODO: find another way to get the rest of the room since the id is already in the List
            List<Room> selectedRoomList = new ArrayList<Room>();
            for(Room room:lesson.getRooms()) {
                selectedRoomList.add(roomService.loadRoom(room.getId()));
            }
            lesson.setRooms(selectedRoomList);
            lessonService.saveLesson(lesson);
        }

        return SUCCESS;
    }

    /**
     * Deletes the selected course from the database.
     *
     * @return the result string.
     */
    public String delete() {
        course = courseService.loadCourse(courseId);
        if (course != null) {
            courseService.deleteCourse(course);
        }
        return SUCCESS;
    }

    /**
     * Deletes the selected lesson of the course from the database.
     *
     * @return the result string.
     */
    public String deleteLesson() {
        Lesson courseLesson = lessonService.loadLesson(courseLessonId);

        if (courseLesson != null) {
            Course courseOfLesson = courseLesson.getCourse();

            lessonService.deleteLesson(courseLesson);

            // Delete course if this was the last lesson
            if (courseOfLesson.getLessons().size() == 1) {
                courseService.deleteCourse(courseOfLesson);
            }
        }
        return SUCCESS;
    }

    /**
     * Displays the selected course in the course form.
     *
     * @return the result string.
     */

     public String load(){
         course = courseService.loadCourse(courseId);
         numberOfRepetitions = course.getLessons().size() - 1;
         startDate = course.retrieveStartDate();
         endDate = course.retrieveEndDate();
         return SUCCESS;
     }

     public void prepareLoad() {
         lecturerList = lecturerService.loadAllLecturers();
         cohortList = cohortService.loadAllCohorts();
         centuryList = centuryService.loadAllCenturies();
         roomList = roomService.loadAllRooms();
     }

    /**
     * Cancels the editing.
     * This method is implemented in order to avoid problems with parameter submit and validation.
     * A direct link to the "ShowRoomList" action does work but results in multiple stack traces in the
     * application's log.
     *
     * @return the result string.
     */
    public String cancel() {
        return SUCCESS;
    }

    /**
     * Start adding a course
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() { return SUCCESS; }

    public void prepareAdd() {
        lecturerList = lecturerService.loadAllLecturers();
        cohortList = cohortService.loadAllCohorts();
        centuryList = centuryService.loadAllCenturies();
        roomList = roomService.loadAllRooms();
        numberOfRepetitions = 0;

        Calendar calendar = Calendar.getInstance();
        setStartDate(new Timestamp(calendar.getTimeInMillis()));
        calendar.add(Calendar.MINUTE, 30);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));
    }

    /**
     * Save course and edit lessons
     *
     * @return the result string.
     */
    public String editLessons() {
        checkCohort();
        checkCentury();
        ensureAudience();

        // Only save if course already exist and has lessons
        if (course.getId() != null) {
            courseService.saveCourse(course);
            course = courseService.loadCourse(course.getId());
        }

        for (String roomStr : rooms) {
            selectedRooms.add(Long.parseLong(roomStr, 10));
        }
        // TODO: Remove lessons if too many? last ones?

        // Add lessons if higher number of repetitions
        for (int i = 0; i <= (numberOfRepetitions - course.getLessons().size()); i++) {
            Lesson lesson = new Lesson();

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTimeInMillis(startDate.getTime());
            startCalendar.add(Calendar.DATE, 7 * (i+course.getLessons().size()));
            lesson.setStartDate(new Timestamp(startCalendar.getTimeInMillis()));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTimeInMillis(endDate.getTime());
            endCalendar.add(Calendar.DATE, 7 * (i+course.getLessons().size()));
            lesson.setEndDate(new Timestamp(endCalendar.getTimeInMillis()));

            course.getLessons().add(lesson);
        }

        if (numberOfRepetitions == 0) {
            courseService.saveCourse(course);
            course.getLessons().get(0).setCourse(course);
            lessonService.saveLesson(course.getLessons().get(0));

            return REDIRECT;
        }

        return SUCCESS;
    }

    public void prepareEditLessons() {
        roomList = roomService.loadAllRooms();
    }

    /**
     * Sets cohort to null if no cohort was selected.
     */
    private void checkCohort() {
        if (course.getCohort().getId() == -1) {
            course.setCohort(null);
        }
    }

    /**
     * Sets century to null if no century was selected.
     */
    private void checkCentury() {
        if (course.getCentury().getId() == -1) {
            course.setCentury(null);
        }
    }

    /**
     * Ensures that century has precedence over cohort if both are selected.
     */
    private void ensureAudience() {
        if (course.getCohort() != null && course.getCentury() != null) {
            course.setCohort(null);
        }
    }

    @Override
    public void validate() {
        // If the room is not set, the room ID has to be set.
        //if (course == null && courseId == null) {
        //    addActionError(getText("msg.selectCourse"));
        //}
    }

    public ArrayList<Long> getRoomIdsFromList(List<Room> rooms) {
        ArrayList<Long> roomIds = new ArrayList<Long>();
        for(Room room:rooms) {
            roomIds.add(room.getId());
        }
        // If there are no rooms, set the rooms from the course form
        if(roomIds.isEmpty()) {
            for (Long roomId : selectedRooms) {
                roomIds.add(roomId);
            }
        }
        return roomIds;
    }


    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) {this.lessons = lessons; }

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

    public String[] getRooms() {
        return rooms;
    }

    public void setRooms(String[] rooms) {
        this.rooms = rooms;
    }


    public List<Long> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(List<Long> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    public Integer getNumberOfRepetitions() {
        return numberOfRepetitions;
    }
    public void setNumberOfRepetitions(int numberOfLessons) {
        this.numberOfRepetitions = numberOfLessons;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseLessonId() {
        return courseLessonId;
    }
    public void setCourseLessonId(Long courseLessonId) {
        this.courseLessonId = courseLessonId;
    }

    public void setCohortService(CohortService cohortService) {
        this.cohortService = cohortService;
    }
    public List<Cohort> getCohortList() {
        return this.cohortList;
    }

    public void setCenturyService(CenturyService centuryService) {
        this.centuryService = centuryService;
    }
    public List<Century> getCenturyList() {
        return this.centuryList;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }
    public List<Lecturer> getLecturerList() {
        return this.lecturerList;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
    public List<Room> getRoomList() {
        return this.roomList;
    }

    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    public void prepare() throws Exception {
        return;
    }

}
