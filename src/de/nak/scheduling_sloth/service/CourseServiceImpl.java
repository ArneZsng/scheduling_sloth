package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CourseDAO;
import de.nak.scheduling_sloth.model.Course;


import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class CourseServiceImpl implements CourseService {
	/** The course DAO. */
	private CourseDAO courseDAO;

	@Override
	public void saveCourse(Course course) {
		courseDAO.save(course);
	}

    @Override
    public Course loadCourse(Long id) {
        return courseDAO.load(id);
    }

	@Override
	public void deleteCourse(Course course) {
		courseDAO.delete(course);
	}

	@Override
	public List<Course> loadAllCourse() {
		return courseDAO.loadAll();
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

}
