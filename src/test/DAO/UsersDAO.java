package test.DAO;
import main.BusinessLogic.UserChoices;
import main.DAO.*;
import main.DomainModel.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static java.sql.DriverManager.getConnection;
import static main.DomainModel.PlaceType.Pub;

public class UsersDAO {
    // test getUser
    // test getAll
    static Connection conn;

    static {
        try {
            conn = getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ADD and DELETE USERS
    @Test
    public void addDeleteBasicUser() throws Exception {
        try (Statement st = conn.createStatement()) {
            // Use DAO
            BasicUserDAO.add(new User("gianni", "gianni_username"));

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select username from basicusers where username=?");
            getUser.setString(1, "gianni_username");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("gianni_username", rs.getString("username"));
        }
    }

    @Test
    public void addDeleteMunicipality() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Municipality gen = new Municipality("genoa", "Genova");
            MunicipalityDAO.add(gen);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from municipality_intero where municipality_username=?");
            getUser.setString(1, "genoa");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("genoa", rs.getString("municipality_username"));
            Assertions.assertEquals("Genova", rs.getString("municipality_city") );

            // Delete it
            MunicipalityDAO.delete(gen);
        }
    }
    @Test
    public void addDeleteMusician() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Musician mao = new Musician("maoo", "Mao", "rock", 4);
            MusicianDAO.add(mao);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from musicians natural join basicusers where name=?");
            getUser.setString(1, "Mao");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("maoo", rs.getString("username"));
            Assertions.assertEquals("Mao", rs.getString("name"));
            Assertions.assertEquals("rock", rs.getString("genre"));
            Assertions.assertEquals(4, rs.getInt("componentnumb"));

            // Delete it
            MusicianDAO.delete(mao);
        }
    }

    @Test
    public void addDeleteOwner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Owner ciccio = new Owner("ciccio", "Giovannissimo");
            OwnerDAO.add(ciccio);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from owners natural join basicusers where name=?");
            getUser.setString(1, "Giovannissimo");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("ciccio", rs.getString("username"));
            Assertions.assertEquals("Giovannissimo", rs.getString("name"));

            // Delete it
            OwnerDAO.delete(ciccio);
        }
    }

    @Test
    public void addDeletePlanner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Planner molletta = new Planner("molletta", "Malviana");
            PlannerDAO.add(molletta);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from planners natural join basicusers where name=?");
            getUser.setString(1, "molletta");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("Malviana", rs.getString("username"));
            Assertions.assertEquals("molletta", rs.getString("name"));

            // Delete it
            PlannerDAO.delete(molletta);
        }
    }

    //

    @AfterAll
    public static void tearDown() throws Exception {
        try (Statement st = conn.createStatement()) {
            st.execute("delete from basicusers where username=\'gianni_username\'");
        }
        if (conn != null) {
            conn.close();
        }
    }

}
