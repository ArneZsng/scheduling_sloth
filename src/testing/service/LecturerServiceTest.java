package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class LecturerServiceTest {

    @Autowired

    @Test
    public void testSaveLecturer() throws Exception {

    }

    @Test
    public void testLoadLecturer() throws Exception {

    }

    @Test
    public void testDeleteLecturer() throws Exception {

    }

    @Test
    public void testLoadAllLecturers() throws Exception {

    }
}