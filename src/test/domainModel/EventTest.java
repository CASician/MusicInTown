package test.domainModel;

import main.DomainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void publicEvent_test() throws Exception {
        Planner p = new Planner("planner", "user2");
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        PublicEvent e = new PublicEvent("concert", true, LocalDate.of(2025, 4, 28),
                p, pp, "3 days", "Firenze", "Festival");
        Assertions.assertTrue(e.getSubscriptions().isEmpty());
        Assertions.assertEquals("planner", e.getPlanner().getName());
        Assertions.assertEquals( "Firenze", e.getCity());
        Assertions.assertEquals( LocalDate.of(2025, 4, 28), e.getDate());
        Assertions.assertEquals( "Santa Croce", e.getPlace().getName());
    }

    public void privateEvent_test() throws Exception {
        Owner o = new Owner("user", "owner");
        PrivatePlace pp = new PrivatePlace("Firenze",  "Hard Rock", "Piazza Della Repubblica",
                10000, true, PlaceType.Cafe, o);
        PrivateEvent e = new PrivateEvent("concert", true, LocalDate.of(2025, 4, 28),
                o, pp, "3 days", "Firenze", "Festival");
        Assertions.assertTrue(e.getSubscriptions().isEmpty());
        Assertions.assertEquals("owner", e.getOwnerPlanner().getName());
        Assertions.assertEquals( "Firenze", e.getCity());
        Assertions.assertEquals( LocalDate.of(2025, 4, 28), e.getDate());
        Assertions.assertEquals( "Hard Rock", e.getPlace().getName());
    }
}
