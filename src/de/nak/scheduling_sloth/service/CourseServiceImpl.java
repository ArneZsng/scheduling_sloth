package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.CourseDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Course;


import java.util.List;

/**
 * Service interface implementation for Course.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class CourseServiceImpl implements CourseService {
	/** The course DAO. */
	private CourseDAO courseDAO;

	@Override
	public void saveCourse(Course course) throws EntityNotSavableException {
		courseDAO.save(course);
	}

    @Override
    public Course loadCourse(Long id) throws EntityNotFoundException {
        return courseDAO.load(id);
    }

    @Override
    public Course loadWithLessonsAndRooms(long id) throws EntityNotFoundException {
        return courseDAO.loadWithLessonsAndRooms(id);
    }

	@Override
	public void deleteCourse(Course course) throws EntityNotDeletableException {
		courseDAO.delete(course);
	}

	@Override
	public List<Course> loadAllCourses() throws EntityNotFoundException {
		return courseDAO.loadAll();
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

}
