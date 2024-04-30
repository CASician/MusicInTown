package test.businessLogic;

import main.BusinessLogic.EventController;
import main.BusinessLogic.MusicianController;
import main.BusinessLogic.PlacesController;
import main.DAO.MusicianDAO;
import main.DomainModel.Musician;
import main.DomainModel.Planner;
import main.DomainModel.PublicEvent;
import main.DomainModel.PublicPlace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EventControllerTest {

    @BeforeAll
    static void setUpDb() throws SQLException, IOException {
        //Set up all the database
    }

    @BeforeEach
    public void initDb() throws SQLException {
        //Initialize the database
    }

    @Test
    public void eventCreationTest() throws Exception {
        PlacesController pc = new PlacesController();
        EventController ec = new EventController(pc);
        Planner p = new Planner("planner", "user");
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        PublicEvent e = new PublicEvent("concerto", true, LocalDate.of(2025, 4, 28),
                p, pp, "3 days", "Firenze", "Festival");
        Assertions.assertEquals( e.getName(), ec.getPublicEvents().get(e.getId()).getName());
        //Assertions.assertEquals( e, ec.getPublicEventsFiltered(LocalDate.now()).get(0));
    }

    @Test
    public void subscriptionTest() throws Exception {
        Musician m = new Musician("paba", "paolo", "classic", 1);
        PlacesController pc = new PlacesController();
        EventController ec = new EventController(pc);
        Planner p = new Planner("planner", "user");
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        PublicEvent e = new PublicEvent("concerto", true, LocalDate.of(2025, 4, 28),
                p, pp, "3 days", "Firenze", "Festival");
        e.setId(1);
        ec.subscribeEvent(m, e);
        Assertions.assertEquals(m.getName(), e.getSubscribers().get(1).getName());
        Assertions.assertTrue(ec.getPrivateEvents().isEmpty());
    }

}
