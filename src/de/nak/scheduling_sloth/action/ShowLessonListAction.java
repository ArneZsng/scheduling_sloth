package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Cohort;
import de.nak.scheduling_sloth.model.Course;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.model.Room;
import de.nak.scheduling_sloth.service.CohortService;
import de.nak.scheduling_sloth.service.CourseService;
import de.nak.scheduling_sloth.service.LessonService;
import de.nak.scheduling_sloth.service.RoomService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowLessonListAction implements Action {
    /** Select list of rooms. */
    private List<Course> courseList;

    /** The room service. */
    private CourseService courseService;

    @Override
    public String execute() throws Exception {
        courseList = courseService.loadAllCourses();
        return SUCCESS;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
