package testing.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class RoomServiceTest {

    @Autowired

    @Test
    public void testSaveRoom() throws Exception {

    }

    @Test
    public void testLoadRoom() throws Exception {

    }

    @Test
    public void testDeleteRoom() throws Exception {

    }

    @Test
    public void testLoadAllRooms() throws Exception {

    }
}