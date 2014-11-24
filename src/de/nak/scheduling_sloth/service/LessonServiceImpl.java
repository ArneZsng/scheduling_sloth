package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.LessonDAO;
import de.nak.scheduling_sloth.exception.EntityNotDeletableException;
import de.nak.scheduling_sloth.exception.EntityNotFoundException;
import de.nak.scheduling_sloth.exception.EntityNotSavableException;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.utilities.Utilities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Service interface implementation for Lecturer.
 *
 * @author      Patrik Ghahramanian <patrik.ghahramanian@nordakademie.de>
 * @version     1.0
 * @since       2014-10-28
 */
public class LessonServiceImpl implements LessonService {
	/** The lesson DAO. */
	private LessonDAO lessonDAO;

	@Override
	public void saveLesson(Lesson lesson) throws EntityNotSavableException {
		lessonDAO.save(lesson);
	}

    @Override
    public Lesson loadLesson(Long id) throws EntityNotFoundException {
        return lessonDAO.load(id);
    }

	@Override
	public void deleteLesson(Lesson lesson) throws EntityNotDeletableException {
		lessonDAO.delete(lesson);
	}

	@Override
	public List<Lesson> loadAllLessons() throws EntityNotFoundException {
		return lessonDAO.loadAll();
	}

    @Override
    public List<Lesson> loadAllLessonsInWeek(Integer week, Integer year) {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 1); //Let week begin on Monday
        Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

        calendar.add(Calendar.DATE, 7);
        Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

        return lessonDAO.loadAllBetween(startDate, endDate);
    }

	public void setLessonDAO(LessonDAO lessonDAO) {
		this.lessonDAO = lessonDAO;
	}

}
