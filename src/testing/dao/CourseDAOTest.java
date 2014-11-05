package testing.dao;

import de.nak.scheduling_sloth.dao.CourseDAO;
import de.nak.scheduling_sloth.model.Course;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class CourseDAOTest {

    private CourseDAO courseDAO = new CourseDAO();
    private Course course = new Course();


    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            courseDAO.save(course);
        }
        assertEquals(10, courseDAO.loadAll().size());

    }

    @Test
    public void testLoad() throws Exception {
        Long id = 1l;
        course.setId(id);
        courseDAO.save(course);
        assertEquals(course.getId(), courseDAO.load(id));

    }

    @Test
    public void testDelete() throws Exception {
        courseDAO.save(course);
        assertEquals(1, courseDAO.loadAll().size());
        courseDAO.delete(course);
        assertNull(courseDAO.loadAll().size());

    }
}