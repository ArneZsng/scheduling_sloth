package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Course;

import java.util.List;

/**
 * Service interface for Course.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public interface CourseService {

	/**
	 * Creates or updates a rooms.
	 *
	 * @param course The course.
	 */
	void saveCourse(Course course) throws EntityNotSavableException;

	/**
	 * Loads a single courses.
	 *
	 * @param id The identifier.
	 * @return a course or null.
	 */
     Course loadCourse(Long id) throws EntityNotFoundException;

    /**
     * Loads a single courses with Lessons and Rooms.
     *
     * @param id The identifier.
     * @return a course or null.
     */
    Course loadWithLessonsAndRooms(long id) throws EntityNotFoundException;

	/**
	 * Deletes the given course.
	 *
	 * @param course The course.
	 */
	void deleteCourse(Course course) throws EntityNotDeletableException;

	/**
	 * Loads a list of all coursess.
	 *
	 * @return a list which is empty if no course was found.
	 */
	List<Course> loadAllCourses() throws EntityNotFoundException;

}
