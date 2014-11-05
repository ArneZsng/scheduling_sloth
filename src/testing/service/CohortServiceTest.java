package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class CohortServiceTest {

    @Autowired

    @Test
    public void testSaveCohort() throws Exception {

    }

    @Test
    public void testLoadCohort() throws Exception {

    }

    @Test
    public void testDeleteCohort() throws Exception {

    }

    @Test
    public void testLoadAllCohorts() throws Exception {

    }
}