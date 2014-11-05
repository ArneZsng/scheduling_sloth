package testing.dao;

import de.nak.scheduling_sloth.dao.RoomDAO;
import de.nak.scheduling_sloth.model.Room;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import testing.memorydatabase.JpaTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {JpaTestConfig.class})

public class RoomDAOTest {

    @Autowired
    private RoomDAO roomDAO = new RoomDAO();
    private Room room = new Room();

    @Test
    public void testSave() throws Exception {
        for(int i = 1; i < 10; i++){
            roomDAO.save(room);
        }
        assertEquals(10, roomDAO.loadAll().size());

    }

    @Test
    public void testLoad() throws Exception {
        Long id = 1l;
        room.setId(id);
        roomDAO.save(room);
        assertEquals(room.getId(), roomDAO.load(id));

    }

    @Test
    public void testDelete() throws Exception {
        roomDAO.save(room);
        assertEquals(1, roomDAO.loadAll().size());
        roomDAO.delete(room);
        assertNull(roomDAO.loadAll().size());


    }
}