package test.domainModel;

import main.DomainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class MusicianTest {
    Musician m = new Musician("ciccccio", "gamer", "pop", 3);
    Planner p = new Planner("planner", "user");
    PublicPlace pp = new PublicPlace("Santo Spirito", "Firenze", "Santo Spirito",
            1000, false);
    PublicEvent pb_e = new PublicEvent("concerto", true, LocalDate.of(2025, 4, 28),
            p, pp, "3 days", "Firenze", "Festival");

    Owner o = new Owner("ola", "chico");
    PrivatePlace prp = new PrivatePlace("Firenze", "porro", "via porro", 100, true, PlaceType.Cafe, o);
    PrivateEvent pr_e = new PrivateEvent("privee", true, LocalDate.of(2025, 12,12), p, prp, "2", "Florence", "culturalissimo");

    @Test
    public void musician_test() throws Exception {
        Musician m = new Musician("user", "musician", "pop", 4);
        Assertions.assertEquals("musician", m.getName());
        Assertions.assertEquals("user", m.getUsername());
        Assertions.assertEquals(4, m.getComponentNumb());
        ArrayList<PrivateEvent> a = new ArrayList<>();
        Assertions.assertEquals( a, m.getPrivateEvents());
    }

    @Test
    public void addSubscriptionsTest(){
        int sub_to_priv = m.test_getSubscriptions_privateEvents().size();
        int sub_to_pub = m.test_getSubscriptions_publicEvents().size();

        m.addSubscription(pb_e);
        m.addSubscription(pr_e);

        Assertions.assertEquals(sub_to_priv+1, m.test_getSubscriptions_privateEvents().size());
        Assertions.assertEquals(sub_to_pub+1, m.test_getSubscriptions_publicEvents().size());
    }

}
