package testing.dao;

import de.nak.scheduling_sloth.dao.CohortDAO;
import de.nak.scheduling_sloth.model.Cohort;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class CohortDAOTest {

    private CohortDAO cohortDAO = new CohortDAO();
    private Cohort cohort = new Cohort();

    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            cohortDAO.save(cohort);
        }
        assertEquals(10, cohortDAO.loadAll().size());
    }

    @Test
    public void testLoad() throws Exception {
        Long id = 1l;
        cohort.setId(id);
        cohortDAO.save(cohort);
        assertEquals(cohort.getId(), cohortDAO.load(id));

    }

    @Test
    public void testDelete() throws Exception {
        cohortDAO.save(cohort);
        assertEquals(1, cohortDAO.loadAll().size());
        cohortDAO.delete(cohort);
        assertNull(cohortDAO.loadAll().size());

    }
}