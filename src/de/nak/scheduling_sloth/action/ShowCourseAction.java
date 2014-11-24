package de.nak.scheduling_sloth.action;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.CourseService;

import java.util.List;

/**
 * Show action for Course.
 *
 * @author      Arne Zeising <arne.zeising@nordakademie.de>
 * @version     1.0
 * @since       2014-11-18
 */
public class ShowCourseAction extends AbstractAction {
    /** The current course. */
    private Course course;
    /** The course's identifier selected by the user. */
    private Long courseId;
    /** The name of the audience. */
    private String audienceName;
    /** The course service. */
    private CourseService courseService;

    /**
     * Displays the selected course in the course show.
     *
     * @return the result string.
     */
    @Override
    public String execute() {
        try {
            course = courseService.loadWithLessonsAndRooms(courseId);
            setAudienceName(course.retrieveAudienceName());
            return SUCCESS;
        } catch (EntityNotFoundException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public String getAudienceName() {
        return audienceName;
    }
    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
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
