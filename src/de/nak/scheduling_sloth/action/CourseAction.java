package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nak.scheduling_sloth.model.*;
import de.nak.scheduling_sloth.service.*;
import de.nak.scheduling_sloth.utilities.Utilities;
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
    private boolean recheck = false;

    final String REDIRECT = "redirect";

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
            if (lesson != null) {
                lessonIdsToKeep.add(lesson.getId());
            }
        }

        // Determine which lessons need to be deleted
        List<Lesson> lessonsToDelete;

        if (course.getId() == null) {
            lessonsToDelete = new ArrayList<Lesson>();
        } else {
            lessonsToDelete = courseService.loadCourse(course.getId()).getLessons();
            for (Long lessonId : lessonIdsToKeep) {
                for (int i = 0; i < lessonsToDelete.size() && lessonId != null; i++) {
                    if (lessonId.equals(lessonsToDelete.get(i).getId())) {
                        lessonsToDelete.remove(i);
                        break;
                    }
                }
            }
        }

        for (Lesson lesson : course.getLessons()) {
            lesson.setCourse(course);
            List<Room> selectedRoomList = new ArrayList<Room>();
            for (Room room:lesson.getRooms()) {
                selectedRoomList.add(roomService.loadRoom(room.getId()));
            }
            lesson.setRooms(selectedRoomList);

            if (!isValidLesson(course, lesson)) {
                return ERROR;
            }
        }
        if (!isValidCourse(course)) {
            return ERROR;
        }
        // Save course & lessons
        courseService.saveCourse(course);

        for (Lesson lesson : course.getLessons()) {
            lessonService.saveLesson(lesson);
        }
        course = courseService.loadWithLessonsAndRooms(course.getId());

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
    @SkipValidation
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
    @SkipValidation
    public String deleteLesson() {
        Lesson lesson = lessonService.loadLesson(courseLessonId);
        if (lesson != null) {
            Course course = lesson.getCourse();
            // Delete whole course if this was the last lesson
            if (course.getLessons().size() == 1) {
                courseService.deleteCourse(course);
                return REDIRECT;
            } else {
                lessonService.deleteLesson(lesson);
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
    @SkipValidation
    public String load(){
     course = courseService.loadWithLessonsAndRooms(courseId);
     if (course != null) {
         numberOfRepetitions = course.getLessons().size() - 1;
         startDate = course.retrieveStartDate();
         endDate = course.retrieveEndDate();
         selectedRooms = getRoomIdsFromList(course.retrieveRoomsOfFirstLesson());
         return SUCCESS;
     } else {
        return ERROR;
     }
    }

    public void prepareLoad() {
        loadCoreDate();
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
    public String add() {
        return SUCCESS;
    }

    public void prepareAdd() {
        loadCoreDate();

        Calendar calendar = Utilities.getSchedulingCalendar();
        setStartDate(new Timestamp(calendar.getTimeInMillis()));
        calendar.add(Calendar.MINUTE, 30);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));
    }

    private void loadCoreDate() {
        lecturerList = lecturerService.loadAllLecturers();
        cohortList = cohortService.loadAllCohorts();
        centuryList = centuryService.loadAllCenturies();
        roomList = roomService.loadAllRooms();
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

            if (!isValidLesson(course, lesson) || !isValidCourse(course)) {
                return ERROR;
            }

            for (Lesson lessonToDelete : course.getLessons())
                lessonService.deleteLesson(lessonToDelete);

            courseService.saveCourse(course);
            lessonService.saveLesson(lesson);
            return REDIRECT;
        }

        // Add lessons if higher number of repetitions
        for (int i = 0; i <= (numberOfRepetitions - numberOfLessons); i++) {
            Lesson lesson = new Lesson();
            lesson.setCourse(course);

            Calendar startCalendar = Utilities.getSchedulingCalendar();
            startCalendar.setTimeInMillis(startDate.getTime());
            startCalendar.add(Calendar.DATE, 7 * (i + numberOfLessons));
            lesson.setStartDate(new Timestamp(startCalendar.getTimeInMillis()));

            Calendar endCalendar = Utilities.getSchedulingCalendar();
            endCalendar.setTimeInMillis(endDate.getTime());
            endCalendar.add(Calendar.DATE, 7 * (i + numberOfLessons));
            lesson.setEndDate(new Timestamp(endCalendar.getTimeInMillis()));

            course.getLessons().add(lesson);
        }

        // Populate first lesson with settings from course form
        if (numberOfLessons >= 1) {
            Lesson lesson = course.retrieveFirstLesson();
            lesson.setStartDate(startDate);
            lesson.setEndDate(endDate);

            List<Room> rooms = new ArrayList<Room>();
            for (Long roomId: selectedRooms) {
                rooms.add(roomService.loadRoom(roomId));
            }
            lesson.setRooms(rooms);
        }

        // Remove lessons if lower number of repetitions
        Collections.sort(course.getLessons());
        for (int i = numberOfLessons - 1; i > numberOfRepetitions; i--) {
            course.getLessons().remove(i);
        }

        return SUCCESS;
    }

    public void prepareEditLessons() {
        loadCoreDate();
    }

    @SkipValidation
    public String recheck() {
        recheck = true;
        return SUCCESS;
    }

    /**
     * Validates for business logic
     *
     * @param course Course which will be checked
     * @param lesson Lesson which will be checked
     * @return isValidLesson Are course and lessons valid as is
     */
    private Boolean isValidLesson(Course course, Lesson lesson) {
        // Check if start date is before end date
        if (!lesson.startDateBeforeEndDate()) {
            addActionError(getText("msg.startDateBeforeEndDate"));
        }
        // Check if room is set
        if (!lesson.hasRoom()) {
            addActionError(getText("msg.noRoomSelected"));
        }
        if (hasActionErrors()) {
            return false;
        }

        if (!collisionFlag || recheck) {
            course.setLecturer(lecturerService.loadLecturerWithLessons(course.getLecturer().getId()));

            // Fully load century/cohort
            if (!lesson.lecturerAvailable()) {
                addActionError(getText("msg.lecturerNotAvailable"));
            }
            if (!lesson.audienceAvailable()) {
                addActionError(getText("msg.audienceNotAvailable"));
            }
            if (!lesson.allRoomsAvailable()) {
                addActionError(getText("msg.roomsNotAvailable"));
            }
            if (!lesson.allRoomsBigEnough()) {
                addActionError(getText("msg.roomsNotBigEnough"));
            }
            if (hasActionErrors()) {
                collisionFlag = true;
                return false;
            }
        }
        return true;
    }

    /**
     * Validates for business logic of course
     *
     * @param course Course which will be checked
     * @return isValidCourse Is course valid as is
     */
    private Boolean isValidCourse(Course course) {
        if (!collisionFlag || recheck) {
            if (!course.noLessonsCollide()) {
                addActionError(getText("msg.lessonsCollide"));
            }
            if (hasActionErrors()) {
                collisionFlag = true;
                return false;
            }
        }
        return true;
    }

    /**
     * Sets cohort to null if no cohort is selected.
     */
    private void checkCohort() {
        if (course.getCohort() != null && course.getCohort().getId() != null && course.getCohort().getId() == -1) {
            course.setCohort(null);
        }
    }

    /**
     * Sets century to null if no century is selected.
     */
    private void checkCentury() {
        if (course.getCentury() != null && course.getCentury().getId() != null && course.getCentury().getId() == -1) {
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
        // Skip by @SkipValidate, if no form validations necessary
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

    public boolean isRecheck() {
        return recheck;
    }
    public void setRecheck(boolean recheck) {
        this.recheck = recheck;
    }
}
