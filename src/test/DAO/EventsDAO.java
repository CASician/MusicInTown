package test.DAO;

import main.DAO.*;
import main.DomainModel.*;
import org.junit.jupiter.api.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.sql.DriverManager.getConnection;

public class EventsDAO {
    static Connection conn;

    static Planner planner = new Planner("lollo", "brigidda");
    static Owner owner;

    static {
        try {
            owner = new Owner("senza", "complimenti");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, owner);

    static PrivateEvent pvtev_owner = new PrivateEvent("privatissimo", false, LocalDate.of(2023, 12,12), planner, pvtpl, "2", "firenze", "bellissimo");
    static PrivateEvent pvtev_planner = new PrivateEvent("privatissimo_due", false, LocalDate.of(2022, 11,11), owner, pvtpl, "3", "pisa", "incredibile");


    static Planner planner2 = new Planner("ecce", "homo");
    static PublicPlace publicPlace = new PublicPlace("aerosol", "firenze", "piazza pubblica", 200, false);
    static PublicEvent publicEvent = new PublicEvent("fantasia", true, LocalDate.of(2022, 2,2), planner2, publicPlace, "1", "firenze", "comico" );

    static {
        try {
            conn = getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public EventsDAO() throws SQLException {
    }

    @BeforeAll
    public static void setUpDB() throws Exception{
        OwnerDAO.add(owner);
        PrivatePlaceDAO.add(pvtpl);
        PlannerDAO.add(planner);
        PlannerDAO.add(planner2);
        PublicPlaceDAO.add(publicPlace);
    }

    // ---------------------------- ADD and DELETE EVENTS ----------------------------
    @Test
    public void addPrivateEventTest() throws Exception {
        // Use DAO to add an Event
        PrivateEventDAO.add(pvtev_planner);
        PrivateEventDAO.add(pvtev_owner);

        // Use a query to check the data inside the DB
        PreparedStatement getPlace = conn.prepareStatement("select * from privateevents_intero where privateevent_name=\'privatissimo\'");
        PreparedStatement getPlace_due = conn.prepareStatement("select * from privateevents_intero where privateevent_name=\'privatissimo_due\'");
        ResultSet rs = getPlace.executeQuery();
        ResultSet rs_due = getPlace_due.executeQuery();
        rs.next();
        rs_due.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("privatissimo", rs.getString("privateevent_name"));
        Assertions.assertEquals("posto bello", rs.getString("privateevent_place"));
        Assertions.assertEquals("lollo", rs.getString("privateevent_plannername"));
        Assertions.assertNull(rs.getString("privateevent_ownerplannername"));
        Assertions.assertFalse(rs.getBoolean("privateevent_open"));
        Assertions.assertEquals("2023-12-12", rs.getString("privateevent_date"));
        Assertions.assertEquals("firenze", rs.getString("privateevent_city"));
        Assertions.assertEquals("bellissimo", rs.getString("privateevent_type"));
        Assertions.assertEquals("2", rs.getString("privateevent_duration"));
        Assertions.assertFalse(rs.getBoolean("privateevent_accepted"));

        Assertions.assertEquals("privatissimo_due", rs_due.getString("privateevent_name"));
        Assertions.assertEquals("posto bello", rs_due.getString("privateevent_place"));
        Assertions.assertNull(rs_due.getString("privateevent_plannername"));
        Assertions.assertEquals("complimenti", rs_due.getString("privateevent_ownerplannername"));
        Assertions.assertFalse(rs_due.getBoolean("privateevent_open"));
        Assertions.assertEquals("2022-11-11", rs_due.getString("privateevent_date"));
        Assertions.assertEquals("pisa", rs_due.getString("privateevent_city"));
        Assertions.assertEquals("incredibile", rs_due.getString("privateevent_type"));
        Assertions.assertEquals("3", rs_due.getString("privateevent_duration"));
        Assertions.assertFalse(rs_due.getBoolean("privateevent_accepted"));
    }
    @Test
    public void deletePrivateEventTest()throws Exception{
        int num = PrivateEventDAO.getAll().size();
        PrivateEventDAO.delete(pvtev_planner);
        PrivateEventDAO.delete(pvtev_owner);
        Assertions.assertEquals(num-2, PrivateEventDAO.getAll().size());
    }

    @Test
    public void addPublicEventTest() throws Exception {
        // Use DAO to add a Public Event
        PublicEventDAO.add(publicEvent);

        // Use a query to check the data inside the DB
        PreparedStatement getEvent = conn.prepareStatement("select * from publicevents_intero where publicevent_name =\'fantasia\'");
        ResultSet rs = getEvent.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("fantasia", rs.getString("publicevent_name"));
        Assertions.assertEquals("aerosol", rs.getString("publicevent_place"));
        Assertions.assertEquals("homo", rs.getString("publicevent_planner"));
        Assertions.assertTrue(rs.getBoolean("publicevent_open"));
        Assertions.assertEquals("2022-02-02", rs.getString("publicevent_date"));
        Assertions.assertEquals("firenze", rs.getString("publicevent_city"));
        Assertions.assertEquals("comico", rs.getString("publicevent_type"));
        Assertions.assertEquals("1", rs.getString("publicevent_duration"));
        Assertions.assertFalse(rs.getBoolean("publicevent_accepted"));
    }

    @Test
    public void deletePublicEventTest() throws Exception {
        int num = PublicEventDAO.getAll().size();
        PublicEventDAO.delete(publicEvent);
        Assertions.assertEquals(num-1, PublicEventDAO.getAll().size());
    }

    @AfterAll
    public static void clearDB() throws Exception {
        // Delete from DB
        PrivatePlaceDAO.delete(pvtpl);
        OwnerDAO.delete(owner);
        PlannerDAO.delete(planner);
        PlannerDAO.delete(planner2);
        PublicPlaceDAO.delete(publicPlace);
    }
}
