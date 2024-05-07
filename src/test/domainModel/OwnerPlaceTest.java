package test.domainModel;

import main.DomainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OwnerPlaceTest {

    static Planner planner = new Planner("lollo", "brigidda");
    static Owner owner = new Owner("senza", "complimenti");

    static PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, owner);

    static PrivateEvent pvtev_owner = new PrivateEvent("privatissimo", false, LocalDate.of(2023, 12,12), planner, pvtpl, "2", "firenze", "bellissimo");
    static PrivateEvent pvtev_planner = new PrivateEvent("privatissimo_due", false, LocalDate.of(2022, 11,11), owner, pvtpl, "3", "pisa", "incredibile");

    @Test
    public void owner_test() throws Exception {
        Owner o = new Owner("user", "owner");
        Assertions.assertEquals("owner", o.getName());
        Assertions.assertEquals("user", o.getUsername());
    }
    @Test
    public void ownerPlace_test() throws Exception {
        Owner o = new Owner("user", "owner");
        PrivatePlace p = new PrivatePlace("Firenze", "Hard Rock", "Piazza della Repubblica",
                1000, true, PlaceType.Cafe, o);
        Assertions.assertEquals( "Hard Rock", p.getName());
        Assertions.assertEquals( "Firenze", p.getCity());
        Assertions.assertEquals( 1000, p.getCapacity());
        Assertions.assertEquals( PlaceType.Cafe, p.getType());
        Assertions.assertEquals( "owner", p.getOwnerName());
    }

    @Test
    public void publicPlace_test() throws  Exception {
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        Assertions.assertEquals( "Santa Croce", pp.getName());
        Assertions.assertEquals(10000, pp.getCapacity());
        pp.setId(5);
        Assertions.assertEquals( 5, pp.getId());
    }

    @Test
    public void proposeEventsTest() {
        int num_events = owner.getEventsToBeAccepted().size();
        owner.propose_event(pvtev_owner);
        owner.propose_event(pvtev_planner);

        Assertions.assertEquals(num_events+2, owner.getEventsToBeAccepted().size());

        owner.remove_event(pvtev_planner);
        owner.remove_event(pvtev_owner);
    }

    @Test
    public void removeEventsTest() {
        owner.propose_event(pvtev_owner);
        owner.propose_event(pvtev_planner);
        int num_events = owner.getEventsToBeAccepted().size();

        owner.remove_event(pvtev_planner);
        owner.remove_event(pvtev_owner);

        Assertions.assertEquals(num_events-2, owner.getEventsToBeAccepted().size());
    }
}
