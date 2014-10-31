package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.service.CourseService;


/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CourseAction extends ActionSupport {
    private static final long serialVersionUID = -2085704409810512813L;
    /** The current course. */
    private Course course;

    /** The course's identifier selected by the user. */
    private Long courseId;

    /** The course service. */
    private CourseService courseService;

    /**
     * Saves the course to the database.
     *
     * @return the result string.
     */
    public String save() {
        courseService.saveCourse(course);
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
     * Displays the selected course in the course form.
     *
     * @return the result string.
     */

     public String load(){
         course = courseService.loadCourse(courseId);
         return SUCCESS;
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

    @Override
    public void validate() {
        // If the room is not set, the room ID has to be set.
        if (course == null && courseId == null) {
            addActionError(getText("msg.selectCourse"));
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
