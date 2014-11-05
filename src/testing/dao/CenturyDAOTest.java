package testing.dao;

import de.nak.scheduling_sloth.dao.CenturyDAO;
import de.nak.scheduling_sloth.model.Century;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class CenturyDAOTest {

    private CenturyDAO centuryDAO = new CenturyDAO();
    private Century century = new Century();


    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            centuryDAO.save(century);
        }
        assertEquals(10, centuryDAO.loadAll().size());
    }

    @Test
    public void testLoad() throws Exception {
       Long id = 1l;
       century.setId(id);
       centuryDAO.save(century);
       assertEquals(century.getId(), centuryDAO.load(id));
    }

    @Test
    public void testDelete() throws Exception {
        centuryDAO.save(century);
        assertEquals(1, centuryDAO.loadAll().size());
        centuryDAO.delete(century);
        assertNull(centuryDAO.loadAll().size());
    }
}