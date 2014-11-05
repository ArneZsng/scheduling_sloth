package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class CourseServiceTest {

    @Autowired

    @Test
    public void testSaveCourse() throws Exception {

    }

    @Test
    public void testLoadCourse() throws Exception {

    }

    @Test
    public void testDeleteCourse() throws Exception {

    }

    @Test
    public void testLoadAllCourse() throws Exception {

    }
}