package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.model.Lesson;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public interface LessonService {

	/**
	 * Creates or updates a lessons.
	 *
	 * @param lesson The lesson.
	 */
	void saveLesson(Lesson lesson);

	/**
	 * Loads a single lessons.
	 *
	 * @param id The identifier.
	 * @return a lesson or null.
	 */
     Lesson loadLesson(Long id) throws EntityNotFoundException;

	/**
	 * Deletes the given lesson.
	 *
	 * @param lesson The lesson.
	 */
	void deleteLesson(Lesson lesson);

	/**
	 * Loads a list of all lessons.
	 *
	 * @return a list which is empty if no lesson was found.
	 */
	List<Lesson> loadAllLessons() throws EntityNotFoundException;

    /**
     * Loads a list of all lessons in a specific week.
     *
     * @return a list which is empty if no lesson was found.
     */
    List<Lesson> loadAllLessonsInWeek(Integer week, Integer year);

}
