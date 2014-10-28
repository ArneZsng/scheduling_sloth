package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.service.CourseService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowCourseListAction implements Action {
    /** The list of courses. */
    private List<Course> courseList;

    /** The course service. */
    private CourseService courseService;

    @Override
    public String execute() throws Exception {courseList = courseService.loadAllCourse();
        return SUCCESS;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
