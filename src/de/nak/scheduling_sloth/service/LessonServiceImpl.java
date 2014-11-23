package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.LessonDAO;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.utilities.Utilities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class LessonServiceImpl implements LessonService {
	/** The lesson DAO. */
	private LessonDAO lessonDAO;

	@Override
	public void saveLesson(Lesson lesson) {
		lessonDAO.save(lesson);
	}

    @Override
    public Lesson loadLesson(Long id) {
        return lessonDAO.load(id);
    }

	@Override
	public void deleteLesson(Lesson lesson) {
		lessonDAO.delete(lesson);
	}

	@Override
	public List<Lesson> loadAllLessons() {
		return lessonDAO.loadAll();
	}

    @Override
    public List<Lesson> loadAllLessonsInWeek(Integer week, Integer year) {
        Calendar calendar = Utilities.getSchedulingCalendar();
        calendar.setWeekDate(year, week, 1); //Let week begin on Monday
        Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

        calendar.set(Calendar.WEEK_OF_YEAR, week+1);
        Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

        return lessonDAO.loadAllBetween(startDate, endDate);
    }

	public void setLessonDAO(LessonDAO lessonDAO) {
		this.lessonDAO = lessonDAO;
	}

}
