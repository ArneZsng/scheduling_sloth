package testing.dao;

import de.nak.scheduling_sloth.dao.LecturerDAO;
import de.nak.scheduling_sloth.model.Lecturer;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class LecturerDAOTest {

    private LecturerDAO lecturerDAO = new LecturerDAO();
    private Lecturer lecturer = new Lecturer();

    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            lecturerDAO.save(lecturer);
        }
        assertEquals(10, lecturerDAO.loadAll().size());

    }

    @Test
    public void testLoad() throws Exception {
        Long id = 1l;
        lecturer.setId(id);
        lecturerDAO.save(lecturer);
        assertEquals(lecturer.getId(), lecturerDAO.load(id));

    }

    @Test
    public void testDelete() throws Exception {

    }

}