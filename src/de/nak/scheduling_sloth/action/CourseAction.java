package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.model.*;
import de.nak.scheduling_sloth.service.*;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.sql.Timestamp;
import java.util.*;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CourseAction extends ActionSupport implements Preparable {
    private static final long serialVersionUID = -2085704409810512813L;
    /** The current course. */
    private Course course;
    /** The current lessons. */
    private List<Lesson> lessons = new ArrayList<Lesson>();
    /** The passed calendar week. */
    private Integer week = 0;
    /** The passed year. */
    private Integer year = 0;
    /** Start date of the first lesson. */
    private Timestamp startDate;
    /** End date of the first lesson. */
    private Timestamp endDate;
    /** The list of the passed rooms. */
    private String[] rooms;
    /** The list of the selected rooms. */
    private List<Long> selectedRooms = new ArrayList<Long>();
    /** The current number of repetitions. */
    private Integer numberOfRepetitions = 0;
    /** The course's identifier selected by the user. */
    private Long courseId;
    /** The lessons's identifier selected by the user. */
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
    /** Flag for collision detection */
    private boolean collisionFlag = false;

    String REDIRECT = "redirect";

    /**
     * Saves the course to the database.
     *
     * @return the result string.
     */
    @SkipValidation
    public String save() {
        ensureAudience();

        // Remember all the lessons which are left in form to keep
        List<Long> lessonIdsToKeep = new ArrayList<Long>();
        for (Lesson lesson : course.getLessons()) {
            if (lesson != null)
                lessonIdsToKeep.add(lesson.getId());
        }

        courseService.saveCourse(course);
        course = courseService.loadWithLessonsAndRooms(course.getId());


        List<Lesson> lessonsToDelete = courseService.loadCourse(course.getId()).getLessons();
        for (Long lessonId : lessonIdsToKeep) {
            for (int i = 0; i < lessonsToDelete.size() && lessonId != null; i++) {
                if (lessonId.equals(lessonsToDelete.get(i).getId())) {
                    lessonsToDelete.remove(i);
                    break;
                }
            }
        }

        for (Lesson lesson : course.getLessons()) {
            lesson.setCourse(course);
            List<Room> selectedRoomList = new ArrayList<Room>();
            for(Room room:lesson.getRooms()) {
                selectedRoomList.add(roomService.loadRoom(room.getId()));
            }
            lesson.setRooms(selectedRoomList);

            if (collisionFlag == false) {
                course.setLecturer(lecturerService.loadLecturerWithLessons(course.getLecturer().getId()));
                if (!lesson.lecturerAvailable())
                    addActionError(getText("msg.lecturerNotAvailable"));
                if (!lesson.audienceAvailable())
                    addActionError(getText("msg.audienceNotAvailable"));
                if (!lesson.hasRoom())
                    addActionError(getText("msg.noRoomSelected"));
                if (!lesson.allRoomsAvailable())
                    addActionError(getText("msg.roomsNotAvailable"));
            }
            if (hasActionErrors()) {
                collisionFlag = true;
                return ERROR;
            } else {
                lessonService.saveLesson(lesson);
            }
        }

        // Remove all lessons which are not needed
        for (Lesson lesson:lessonsToDelete) {
            lessonService.deleteLesson(lesson);
        }

        return SUCCESS;
    }

    public void prepareSave() {
        roomList = roomService.loadAllRooms();
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
        Lesson lesson = lessonService.loadLesson(courseLessonId);
        if (lesson != null) {
            Course course = lesson.getCourse();
            lessonService.deleteLesson(lesson);
            // Delete course if this was the last lesson
            if (course.getLessons().size() == 1) {
                courseService.deleteCourse(course);
                return REDIRECT;
            }
            if (courseId != null) {
                return SUCCESS;
            } else {
                return REDIRECT;
            }
        }
        return ERROR;
    }

    /**
     * Displays the selected course in the course form.
     *
     * @return the result string.
     */

     public String load(){
         course = courseService.loadCourse(courseId);
         if (course != null) {
             numberOfRepetitions = course.getLessons().size() - 1;
             startDate = course.retrieveStartDate();
             endDate = course.retrieveEndDate();
             return SUCCESS;
         } else {
            return ERROR;
         }
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
        ensureAudience();

        // Only save if course already exist and has lessons
        if (course.getId() != null) {
            courseService.saveCourse(course);
            course = courseService.loadWithLessonsAndRooms(course.getId());
        }

        for (String roomStr : rooms) {
            selectedRooms.add(Long.parseLong(roomStr, 10));
        }

        Integer numberOfLessons = course.getLessons().size();

        // Instantly save if no repetitions
        if (numberOfRepetitions == 0) {
            Lesson lesson = new Lesson();
            lesson.setCourse(course);
            lesson.setStartDate(startDate);
            lesson.setEndDate(endDate);

            List<Room> rooms = new ArrayList<Room>();
            for (Long roomId: selectedRooms) {
                rooms.add(roomService.loadRoom(roomId));
            }
            lesson.setRooms(rooms);

            // Check if start date is before end date
            if (!lesson.startDateBeforeEndDate()) {
                addActionError(getText("msg.startDateBeforeEndDate"));
            }

            if (collisionFlag == false && !hasActionErrors()) {
                course.setLecturer(lecturerService.loadLecturerWithLessons(course.getLecturer().getId()));

                // Fully load century/cohort
                if (!lesson.lecturerAvailable())
                    addActionError(getText("msg.lecturerNotAvailable"));
                if (!lesson.audienceAvailable())
                    addActionError(getText("msg.audienceNotAvailable"));
                if (!lesson.hasRoom())
                    addActionError(getText("msg.noRoomSelected"));
                if (!lesson.allRoomsAvailable())
                    addActionError(getText("msg.roomsNotAvailable"));
                if (hasActionErrors())
                    collisionFlag = true;
            }
            if (hasActionErrors()) {
                return ERROR;
            } else {
                for (Lesson lessonToDelete : course.getLessons())
                    lessonService.deleteLesson(lessonToDelete);

                courseService.saveCourse(course);
                lessonService.saveLesson(lesson);
                return REDIRECT;
            }
        }

        // Add lessons if higher number of repetitions
        for (int i = 0; i <= (numberOfRepetitions - numberOfLessons); i++) {
            Lesson lesson = new Lesson();
            lesson.setCourse(course);

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTimeInMillis(startDate.getTime());
            startCalendar.add(Calendar.DATE, 7 * (i+numberOfLessons));
            lesson.setStartDate(new Timestamp(startCalendar.getTimeInMillis()));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTimeInMillis(endDate.getTime());
            endCalendar.add(Calendar.DATE, 7 * (i+numberOfLessons));
            lesson.setEndDate(new Timestamp(endCalendar.getTimeInMillis()));

            course.getLessons().add(lesson);
        }
        // Remove lessons if lower number of repetitions
        Collections.sort(course.getLessons());
        for (int i = numberOfLessons-1; i > numberOfRepetitions; i--) {
            course.getLessons().remove(i);
        }

        return SUCCESS;
    }

    public void prepareEditLessons() {
        roomList = roomService.loadAllRooms();
        lecturerList = lecturerService.loadAllLecturers();
        cohortList = cohortService.loadAllCohorts();
        centuryList = centuryService.loadAllCenturies();
    }

    /**
     * Sets cohort to null if no cohort is selected.
     */
    private void checkCohort() {
        if ((course.getCohort() != null) && (course.getCohort().getId() != null) && (course.getCohort().getId() == -1)) {
            course.setCohort(null);
        }
    }

    /**
     * Sets century to null if no century is selected.
     */
    private void checkCentury() {
        if ((course.getCentury() != null) && (course.getCentury().getId() != null) && (course.getCentury().getId() == -1)) {
            course.setCentury(null);
        }
    }

    /**
     * Ensures that century has precedence over cohort if both are selected.
     */
    private void ensureAudience() {
        checkCohort();
        checkCentury();
        if (course.getCohort() != null && course.getCentury() != null) {
            if (course.getCentury().getId() == null) {
                course.setCentury(null);
            }
            if (course.getCohort().getId() == null || course.getCentury() != null) {
                course.setCohort(null);
            }
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


    @Override
    public void prepare() throws Exception {
        return;
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

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeek() {
        return week;
    }
    public void setWeek(Integer week) {
        this.week = week;
    }

    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        if ("".equals(startDate)) {
            this.startDate = null;
        }
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        if ("".equals(endDate)) {
            this.endDate = null;
        }
        this.endDate = endDate;
    }

    public String[] getRooms() {
        return rooms;
    }
    public void setRooms(String[] rooms) {
        this.rooms = rooms;
    }

    public boolean getCollisionFlag() { return collisionFlag; }
    public void setCollisionFlag(boolean collisionFlag) { this.collisionFlag = collisionFlag; }

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
}
