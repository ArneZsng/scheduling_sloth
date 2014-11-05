package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;


@ContextConfiguration(classes = {JpaTestConfig.class})


    public class CenturyServiceTest {

    @Autowired


    @Test
    public void testSaveCentury() throws Exception {

    }

    @Test
    public void testLoadCentury() throws Exception {
    }

    @Test
    public void testDeleteCentury() throws Exception {

    }

    @Test
    public void testLoadAllCenturies() throws Exception {

    }
}