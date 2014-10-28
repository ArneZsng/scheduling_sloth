package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.model.Course;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public interface CourseService {

	/**
	 * Creates or updates a rooms.
	 *
	 * @param course The course.
	 */
	void saveCourse(Course course);

	/**
	 * Loads a single courses.
	 *
	 * @param id The identifier.
	 * @return a course or null.
	 */
     Course loadCourse(Long id);

	/**
	 * Deletes the given course.
	 *
	 * @param course The course.
	 */
	void deleteCourse(Course course);

	/**
	 * Loads a list of all coursess.
	 *
	 * @return a list which is empty if no course was found.
	 */
	List<Course> loadAllCourse();

}
