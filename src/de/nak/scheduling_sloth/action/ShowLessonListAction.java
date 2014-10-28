package de.nak.scheduling_sloth.action;

import com.opensymphony.xwork2.Action;
import de.nak.scheduling_sloth.model.Lesson;
import de.nak.scheduling_sloth.service.LessonService;

import java.util.List;

/**
 * Created by patrickghahramanian on 28.10.14.
 */
public class ShowLessonListAction implements Action {
    /** The list of lessons. */
    private List<Lesson> lessonList;

    /** The lesson service. */
    private LessonService lessonService;

    @Override
    public String execute() throws Exception {lessonList = lessonService.loadAllLesson();
        return SUCCESS;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }
}
