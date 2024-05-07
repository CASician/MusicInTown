package test.businessLogic;

import main.BusinessLogic.EventController;
import main.BusinessLogic.PlacesController;
import main.DAO.*;
import main.DomainModel.Musician;
import main.DomainModel.Planner;
import main.DomainModel.PublicEvent;
import main.DomainModel.PublicPlace;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MusicianControllerTest {

    static Musician m = new Musician("signor", "test", "classic", 1);
    static Planner p = new Planner("planner", "user");
    static PublicPlace pp = new PublicPlace("Santo Spirito", "Firenze", "Santo Spirito",
            1000, false);
    static PublicEvent e = new PublicEvent("concerto", true, LocalDate.of(2025, 4, 28),
            p, pp, "3 days", "Firenze", "Festival");
    PlacesController pc = new PlacesController();
    EventController ec = new EventController(pc);
    @BeforeAll
    public static void initDB() throws Exception{
        MusicianDAO.add(m);
        PlannerDAO.add(p);
        PublicPlaceDAO.add(pp);
        PublicEventDAO.add(e);
    }

    @Test
    public void subscribingTest() throws Exception{
        int subs_musician = m.getPublicEvents().size();
        ec.subscribeEvent(m, e);

        Assertions.assertEquals(subs_musician+1, m.getPublicEvents().size());
    }

    @AfterAll
    public static void cleanDB() throws Exception{
        EventsToBeAcceptedDAO.delete(e.getId());
        SubscriptionsDAO.delete(m,e);
        MusicianDAO.delete(m);
        PlannerDAO.delete(p);
        PublicEventDAO.delete(e);
        PublicPlaceDAO.delete(pp);
    }
}

// test for subscribing to event