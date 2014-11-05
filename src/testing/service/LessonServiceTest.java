package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class LessonServiceTest {

    @Autowired

    @Test
    public void testSaveLesson() throws Exception {

    }

    @Test
    public void testLoadLesson() throws Exception {

    }

    @Test
    public void testDeleteLesson() throws Exception {

    }

    @Test
    public void testLoadAllLesson() throws Exception {

    }
}