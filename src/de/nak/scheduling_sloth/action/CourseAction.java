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

    /** The current number of lessons. */
    private int numberOfLessons;

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
    /** Select list of lecturer. */
    private List<Room> roomList = new ArrayList<Room>();
    /** The lecturer service. */
    private RoomService roomService;
    /** The lesson service. */
    private LessonService lessonService;


    /**
     * Saves the course to the database.
     *
     * @return the result string.
     */
    @SkipValidation
    public String save() {
        lecturerList = lecturerService.loadAllLecturers();
        cohortList = cohortService.loadAllCohorts();
        centuryList = centuryService.loadAllCenturies();

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
         numberOfLessons = course.getLessons().size();
         return SUCCESS;
     }
     public void prepareLoad() {
         lecturerList = lecturerService.loadAllLecturers();
         cohortList = cohortService.loadAllCohorts();
         centuryList = centuryService.loadAllCenturies();
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
     * Start adding a Century
     *
     * @return the result string.
     */
    @SkipValidation
    public String add() { return SUCCESS; }
    public void prepareAdd() {
        lecturerList = lecturerService.loadAllLecturers();
        cohortList = cohortService.loadAllCohorts();
        centuryList = centuryService.loadAllCenturies();
    }

    /**
     * Save course and edit lessons
     *
     * @return the result string.
     */
    public String editLessons() {
        // TODO: check if every case is doable + improve performance -> save&load
        courseService.saveCourse(course);
        course = courseService.loadCourse(course.getId());

        if (numberOfLessons > 0) {
            // TODO: check performance of size
            if (course.getLessons().size() < numberOfLessons) {
                for (int i = 0; i < numberOfLessons - course.getLessons().size(); i++) {
                    Lesson lesson = new Lesson();
                    java.util.Date date= new java.util.Date();

                    lesson.setStartDate(new Timestamp(date.getTime()));
                    lesson.setEndDate(new Timestamp(date.getTime()));

                    course.getLessons().add(lesson);
                }
            }
        }
        return SUCCESS;
    }
    public void prepareEditLessons() {
        roomList = roomService.loadAllRooms();
    }

    @Override
    public void validate() {
        // If the room is not set, the room ID has to be set.
        //if (course == null && courseId == null) {
        //    addActionError(getText("msg.selectCourse"));
        //}
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

    public int getNumberOfLessons() {
        return numberOfLessons;
    }
    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    // TODO: should we do this here?
    public ArrayList<Long> getRoomIdsFromList(List<Room> rooms) {
        ArrayList<Long> roomIds = new ArrayList<Long>();
        for(Room room:rooms) {
            roomIds.add(room.getId());
        }
        return roomIds;
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
