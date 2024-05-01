package test.DAO;

import main.DAO.DBconnection;
import main.DAO.OwnerDAO;
import main.DAO.PrivatePlaceDAO;
import main.DAO.PublicPlaceDAO;
import main.DomainModel.*;
import org.h2.command.Prepared;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.sql.DriverManager.getConnection;

public class PlacesDAO {
    static Connection conn;

    static {
        try {
            conn = getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------------------------- ADD and DELETE PLACES ----------------------------
    @Test
    public void addAndDeletePrivatePlace() throws Exception{
        // Use DAO to add a Place
        Owner ciccio = new Owner("ciccio", "Giovannissimo");
        OwnerDAO.add(ciccio);
        PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, ciccio);
        PrivatePlaceDAO.add(pvtpl);

        // Use a query to check the data inside the DB
        PreparedStatement getPlace = conn.prepareStatement("select * from privateplace_intero where privateplace_name=\'posto bello\'");
        ResultSet rs = getPlace.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("posto bello", rs.getString("privateplace_name"));
        Assertions.assertEquals("Cafe", rs.getString("privateplace_type"));
        Assertions.assertEquals("firenze", rs.getString("privateplace_city"));
        Assertions.assertEquals("via esta", rs.getString("privateplace_address"));
        Assertions.assertEquals(20, rs.getInt("privateplace_capacity"));
        Assertions.assertEquals(true, rs.getBoolean("privateplace_indoor"));
        Assertions.assertEquals("Giovannissimo", rs.getString("privateplace_ownername"));

        // Delete from DB
        PrivatePlaceDAO.delete(pvtpl);
        OwnerDAO.delete(ciccio);
    }

    @Test
    public void addAndDeletePublicPlace() throws Exception{
        // Use DAO to add a Place
        PublicPlace pblpl = new PublicPlace("bellou", "firenze", "via torta", 10, false);
        PublicPlaceDAO.add(pblpl);

        // Use a query to check the data inside the DB
        PreparedStatement getPlace = conn.prepareStatement("select * from publicplace_intero where publicplace_name = \'bellou\'");
        ResultSet rs = getPlace.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("bellou", rs.getString("publicplace_name"));
        Assertions.assertEquals("firenze", rs.getString("publicplace_city"));
        Assertions.assertEquals("via torta", rs.getString("publicplace_address"));
        Assertions.assertEquals(10, rs.getInt("publicplace_capacity"));
        Assertions.assertEquals(false, rs.getBoolean("publicplace_indoor"));

        // Delete from DB
        PublicPlaceDAO.delete(pblpl);
    }


    // ---------------------------- GETTERS ----------------------------
    @Test
    public void getPublicPlace() throws Exception{
        // Create and use DAO to give to DB
        PublicPlace pblpl = new PublicPlace("bellou", "firenze", "via torta", 10, false);
        PublicPlaceDAO.add(pblpl);

        // Use DAO to create a new Test Object
        PublicPlace testPubPl = PublicPlaceDAO.getPublicPlace("bellou");

        // Verify that the data corresponds.
        Assertions.assertEquals("bellou", testPubPl.getName());
        Assertions.assertEquals("firenze", testPubPl.getCity());
        Assertions.assertEquals("via torta", testPubPl.getAddress());
        Assertions.assertEquals(10, testPubPl.getCapacity());
        Assertions.assertEquals(false, testPubPl.isIndoor());

        // Delete it
        PublicPlaceDAO.delete(pblpl);
    }

    @Test
    public void getPrivatePlace() throws Exception{
        // Create and use DAO to give to DB
        Owner ciccio = new Owner("ciccio", "Giovannissimo");
        OwnerDAO.add(ciccio);
        PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, ciccio);
        PrivatePlaceDAO.add(pvtpl);

        // Use DAO to create a new Test Object
        PrivatePlace testPrivPlc = PrivatePlaceDAO.getPrivatePlace("posto bello");

        // Verify that the data corresponds
        Assertions.assertEquals("posto bello",testPrivPlc.getName());
        Assertions.assertEquals(PlaceType.Cafe, testPrivPlc.getType());
        Assertions.assertEquals("firenze", testPrivPlc.getCity());
        Assertions.assertEquals("via esta", testPrivPlc.getAddress());
        Assertions.assertEquals(20, testPrivPlc.getCapacity());
        Assertions.assertEquals(true, testPrivPlc.isIndoor());
        Assertions.assertEquals("Giovannissimo", testPrivPlc.getOwnerName());

        // Delete it
        PrivatePlaceDAO.delete(pvtpl);
        OwnerDAO.delete(ciccio);
    }

}
