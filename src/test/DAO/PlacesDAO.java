package test.DAO;

import main.DAO.DBconnection;
import main.DAO.OwnerDAO;
import main.DAO.PrivatePlaceDAO;
import main.DAO.PublicPlaceDAO;
import main.DomainModel.*;
import org.h2.command.Prepared;
import org.junit.jupiter.api.*;

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
    static Owner ciccio = new Owner("ciccio", "Giovannissimo");

    PrivatePlace pvtpl = new PrivatePlace("firenze", "posto bello", "via esta", 20, true, PlaceType.Cafe, ciccio);
    PublicPlace pblpl = new PublicPlace("bellou", "firenze", "via torta", 10, false);

    public PlacesDAO() throws SQLException {
    }

    @BeforeAll
    public static void setUpDB() throws Exception{
        OwnerDAO.add(ciccio);
    }

    // ---------------------------- ADD PLACES ----------------------------
    @Test
    public void addPrivatePlaceTest() throws Exception{
        // Use DAO to add a Place
        PrivatePlaceDAO.add(pvtpl);

        // Use a query to check the data inside the DB
        PreparedStatement getPlace = conn.prepareStatement("select * from privateplace_intero where privateplace_name='posto bello'");
        ResultSet rs = getPlace.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("posto bello", rs.getString("privateplace_name"));
        Assertions.assertEquals("Cafe", rs.getString("privateplace_type"));
        Assertions.assertEquals("firenze", rs.getString("privateplace_city"));
        Assertions.assertEquals("via esta", rs.getString("privateplace_address"));
        Assertions.assertEquals(20, rs.getInt("privateplace_capacity"));
        Assertions.assertTrue(rs.getBoolean("privateplace_indoor"));
        Assertions.assertEquals("Giovannissimo", rs.getString("privateplace_ownername"));
    }
    @Test
    public void addPublicPlaceTest() throws Exception{
        // Use DAO to add a Place
        PublicPlaceDAO.add(pblpl);

        // Use a query to check the data inside the DB
        PreparedStatement getPlace = conn.prepareStatement("select * from publicplace_intero where publicplace_name = 'bellou'");
        ResultSet rs = getPlace.executeQuery();
        rs.next();

        // Verify that the data corresponds.
        Assertions.assertEquals("bellou", rs.getString("publicplace_name"));
        Assertions.assertEquals("firenze", rs.getString("publicplace_city"));
        Assertions.assertEquals("via torta", rs.getString("publicplace_address"));
        Assertions.assertEquals(10, rs.getInt("publicplace_capacity"));
        Assertions.assertFalse(rs.getBoolean("publicplace_indoor"));
    }

    // ---------------------------- GETTERS ----------------------------
    @Test
    public void getPublicPlaceTest() throws Exception{
        // Use DAO to create a new Test Object
        PublicPlace testPubPl = PublicPlaceDAO.getPublicPlace("Lungarno");

        // Verify that the data corresponds.
        Assertions.assertEquals("Lungarno", testPubPl.getName());
        Assertions.assertEquals("Firenze", testPubPl.getCity());
        Assertions.assertEquals("via Lungarno", testPubPl.getAddress());
        Assertions.assertEquals(40, testPubPl.getCapacity());
        Assertions.assertFalse(testPubPl.isIndoor());
    }

    @Test
    public void getPrivatePlaceTest() throws Exception{
        // Use DAO to create a new Test Object
        PrivatePlace testPrivPlc = PrivatePlaceDAO.getPrivatePlace("Inferno");

        // Verify that the data corresponds
        Assertions.assertEquals("Inferno",testPrivPlc.getName());
        Assertions.assertEquals(PlaceType.Restaurant, testPrivPlc.getType());
        Assertions.assertEquals("Firenze", testPrivPlc.getCity());
        Assertions.assertEquals("via del Girone", testPrivPlc.getAddress());
        Assertions.assertEquals(100, testPrivPlc.getCapacity());
        Assertions.assertTrue(testPrivPlc.isIndoor());
        Assertions.assertEquals("Virgilio", testPrivPlc.getOwnerName());
    }

    // ---------------------------- DELETE ----------------------------
    @Test
    public void deletePrivatePlaceTest() throws Exception {
        int num = PrivatePlaceDAO.getAll().size();
        PrivatePlaceDAO.delete(pvtpl);
        Assertions.assertEquals(num-1, PrivatePlaceDAO.getAll().size());
    }

    @Test
    public void deletePublicPlaceTest() throws Exception {
        int num = PublicPlaceDAO.getAll().size();
        PublicPlaceDAO.delete(pblpl);
        Assertions.assertEquals(num-1, PublicPlaceDAO.getAll().size());
    }


    @AfterAll
    public static void clearDB() throws Exception{
        OwnerDAO.delete(ciccio);
    }
}
