package test.domainModel;

import main.DomainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class MusicianTest {

    @Test
    public void musician_test() throws Exception {
        Musician m = new Musician("musician", "user", "pop", 4);
        Assertions.assertEquals("musician", m.getName());
        Assertions.assertEquals("user", m.getUsername());
        Assertions.assertEquals(4, m.getComponentNumb());
        ArrayList<PrivateEvent> a = new ArrayList<>();
        Assertions.assertEquals( a, m.getPrivateEvents());
    }
}
