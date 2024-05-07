package test.businessLogic;

import main.BusinessLogic.EventController;
import main.BusinessLogic.MusicianController;
import main.BusinessLogic.PlacesController;
import main.DAO.*;
import main.DomainModel.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EventControllerTest {

    static Musician m = new Musician("signor", "test", "classic", 1);
    static Planner p = new Planner("planner", "user");
    static PublicPlace pp = new PublicPlace("Santo Spirito", "Firenze", "Santo Spirito",
            1000, false);
    static PublicEvent e = new PublicEvent("concerto", true, LocalDate.of(2025, 4, 28),
            p, pp, "3 days", "Firenze", "Festival");
    PlacesController pc = new PlacesController();
    EventController ec = new EventController(pc);

    @BeforeAll
    public static void addDB() throws Exception{
        MusicianDAO.add(m);
        PlannerDAO.add(p);
        PublicPlaceDAO.add(pp);
        PublicEventDAO.add(e);
    }

    @Test
    public void subscriptionTest() throws Exception {
        int subs_musician = m.getPublicEvents().size();
        int subs_event = e.getSubscribers().size();
        ec.subscribeEvent(m, e);

        Assertions.assertEquals(subs_musician+1, m.getPublicEvents().size());
        Assertions.assertEquals(subs_event+1, e.getSubscribers().size());
    }

    @Test
    public void testNotifyEventObserver() throws Exception{
        Municipality m = MunicipalityDAO.getMunicipality("florence");
        int nr_eventsTBA = m.getEventsToBeAccepted().size();
        ec.notifyEventObservers(e);
        // This is not DRY, but it is needed because my m's array does not get updated, it only happens in the DB.
        // It is optimized for our use case, not for testing though.
        // In other words, this m, is completely different by the one that 'notifyEventObserver' updates. Therefore I need to call again
        // the DAO to "update" it.
        m = MunicipalityDAO.getMunicipality("florence");

        Assertions.assertEquals(nr_eventsTBA+1, m.getEventsToBeAccepted().size());
    }

    // test for visualizing events: no.
    // test for visualizing filtered events: no
    // test for visualizing places: no
    // test for visualizing subscriptions: no.

    @AfterAll
    public static void removeDB() throws Exception{
        EventsToBeAcceptedDAO.delete(e.getId());
        SubscriptionsDAO.delete(m,e);
        MusicianDAO.delete(m);
        PlannerDAO.delete(p);
        PublicEventDAO.delete(e);
        PublicPlaceDAO.delete(pp);
    }
}
