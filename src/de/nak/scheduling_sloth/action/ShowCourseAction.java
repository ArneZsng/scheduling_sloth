package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.CourseService;

import java.util.List;

/**
 * Created by arne on 10/28/14.
 */
public class ShowCourseAction extends AbstractAction implements Action {
    /** The current course. */
    private Course course;
    /** The course's identifier selected by the user. */
    private Long courseId;
    /** The name of the audience. */
    private String audienceName;
    /** The lesson list. */
    private List<Lesson> lessonList;
    /** The course service. */
    private CourseService courseService;

    /**
     * Displays the selected course in the course show.
     *
     * @return the result string.
     */
    @Override
    public String execute() throws Exception {
        course = courseService.loadWithLessonsAndRooms(courseId);
        setAudienceName(course.retrieveAudienceName());
        return SUCCESS;
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
