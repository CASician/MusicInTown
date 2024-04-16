package test.businessLogic;

import main.DomainModel.Musician;
import main.DomainModel.Planner;
import main.DomainModel.PublicEvent;
import main.DomainModel.PublicPlace;
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
        Musician m = new Musician("musician", "user1", "pop", 4);
        Planner p = new Planner("planner", "user2");
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        PublicEvent e = new PublicEvent("concert", true, LocalDate.of(2025, 4, 28),
                p, pp, "3 days", "Firenze", "Festival");
    }
}
