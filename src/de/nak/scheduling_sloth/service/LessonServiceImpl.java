package de.nak.scheduling_sloth.service;

import de.nak.scheduling_sloth.dao.LessonDAO;
import de.nak.scheduling_sloth.model.Lesson;

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
	public List<Lesson> loadAllLesson() {
		return lessonDAO.loadAll();
	}

	public void setLessonDAO(LessonDAO lessonDAO) {
		this.lessonDAO = lessonDAO;
	}

}
