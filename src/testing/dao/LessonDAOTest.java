package testing.dao;

import de.nak.scheduling_sloth.dao.LessonDAO;
import de.nak.scheduling_sloth.model.Lesson;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class LessonDAOTest {

    private LessonDAO lessonDAO = new LessonDAO();
    private Lesson lesson = new Lesson();

    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            lessonDAO.save(lesson);
        }
        assertEquals(10, lessonDAO.loadAll().size());

    }

    @Test
    public void testLoad() throws Exception {
        Long id = 1l;
        lesson.setId(id);
        lessonDAO.save(lesson);
        assertEquals(lesson.getId(), lessonDAO.load(id));

    }

    @Test
    public void testDelete() throws Exception {
        lessonDAO.save(lesson);
        assertEquals(1, lessonDAO.loadAll().size());
        lessonDAO.delete(lesson);
        assertNull(lessonDAO.loadAll().size());

    }
}