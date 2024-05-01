package test.DAO;

import main.DAO.*;
import main.DomainModel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.sql.DriverManager.getConnection;

public class EventsDAO {
    static Connection conn;

    static {
        try {
            conn = getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------------------------- ADD and DELETE EVENTS ----------------------------
    @Test
    public void addAndDeletePrivateEvent() throws Exception {
        // Use DAO to add an Event
        Planner planner = new Planner("lollo", "brigidda");
        Owner owner = new Owner("senza", "complimenti");
        OwnerDAO.add(owner);
        PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, owner);
        PrivatePlaceDAO.add(pvtpl);
        PlannerDAO.add(planner);


        PrivateEvent pvtev_owner = new PrivateEvent("privatissimo", false, LocalDate.of(2023, 12,12), planner, pvtpl, "2", "firenze", "bellissimo");
        PrivateEvent pvtev_planner = new PrivateEvent("privatissimo_due", false, LocalDate.of(2022, 11,11), owner, pvtpl, "3", "pisa", "incredibile");
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
        Assertions.assertEquals(null, rs.getString("privateevent_ownerplannername"));
        Assertions.assertEquals(false, rs.getBoolean("privateevent_open"));
        Assertions.assertEquals("2023-12-12", rs.getString("privateevent_date"));
        Assertions.assertEquals("firenze", rs.getString("privateevent_city"));
        Assertions.assertEquals("bellissimo", rs.getString("privateevent_type"));
        Assertions.assertEquals("2", rs.getString("privateevent_duration"));
        Assertions.assertEquals(false, rs.getBoolean("privateevent_accepted"));

        Assertions.assertEquals("privatissimo_due", rs_due.getString("privateevent_name"));
        Assertions.assertEquals("posto bello", rs_due.getString("privateevent_place"));
        Assertions.assertEquals(null, rs_due.getString("privateevent_plannername"));
        Assertions.assertEquals("complimenti", rs_due.getString("privateevent_ownerplannername"));
        Assertions.assertEquals(false, rs_due.getBoolean("privateevent_open"));
        Assertions.assertEquals("2022-11-11", rs_due.getString("privateevent_date"));
        Assertions.assertEquals("pisa", rs_due.getString("privateevent_city"));
        Assertions.assertEquals("incredibile", rs_due.getString("privateevent_type"));
        Assertions.assertEquals("3", rs_due.getString("privateevent_duration"));
        Assertions.assertEquals(false, rs_due.getBoolean("privateevent_accepted"));

        // Delete from DB
        PrivatePlaceDAO.delete(pvtpl);
        OwnerDAO.delete(owner);
        PlannerDAO.delete(planner);
        PrivateEventDAO.delete(pvtev_planner);
        PrivateEventDAO.delete(pvtev_owner);
    }

    @Test
    public void addAndDeletePublicEvent() throws Exception {
        // Use DAO to add a Public Event
        Planner planner = new Planner("ecce", "homo");
        PublicPlace publicPlace = new PublicPlace("aerosol", "firenze", "piazza pubblica", 200, false);
        PublicEvent publicEvent = new PublicEvent("fantasia", true, LocalDate.of(2022, 2,2), planner, publicPlace, "1", "firenze", "comico" );

        PlannerDAO.add(planner);
        PublicPlaceDAO.add(publicPlace);
        PublicEventDAO.add(publicEvent);

        // Use a query to check the data inside the DB
        PreparedStatement getEvent = conn.prepareStatement("select * from publicevents_intero where publicevent_name =\'fantasia\'");
        ResultSet rs = getEvent.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("fantasia", rs.getString("publicevent_name"));
        Assertions.assertEquals("aerosol", rs.getString("publicevent_place"));
        Assertions.assertEquals("homo", rs.getString("publicevent_planner"));
        Assertions.assertEquals(true, rs.getBoolean("publicevent_open"));
        Assertions.assertEquals("2022-02-02", rs.getString("publicevent_date"));
        Assertions.assertEquals("firenze", rs.getString("publicevent_city"));
        Assertions.assertEquals("comico", rs.getString("publicevent_type"));
        Assertions.assertEquals("1", rs.getString("publicevent_duration"));
        Assertions.assertEquals(false, rs.getBoolean("publicevent_accepted"));

        // Delete from DB
        PlannerDAO.delete(planner);
        PublicPlaceDAO.delete(publicPlace);
        PublicEventDAO.delete(publicEvent);
    }


}
