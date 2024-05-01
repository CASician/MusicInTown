package test.DAO;
import main.BusinessLogic.BasicUserController;
import main.BusinessLogic.UserChoices;
import main.DAO.*;
import main.DomainModel.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static java.sql.DriverManager.getConnection;
import static main.DomainModel.PlaceType.Pub;

public class UsersDAO {
    // test getAll
    static Connection conn;

    static {
        try {
            conn = getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------------------------- ADD and DELETE USERS ----------------------------
    @Test
    public void addDeleteBasicUser() throws Exception {
        try (Statement st = conn.createStatement()) {
            // Use DAO
            User gianni = new User("gianni", "gianni_username");
            BasicUserDAO.add(gianni);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select username from basicusers where username=?");
            getUser.setString(1, "gianni_username");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("gianni_username", rs.getString("username"));

            // Delete from DB
            BasicUserDAO.delete(gianni);
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

    // ---------------------------- GETTERS ----------------------------
    @Test
    public void getMunicipality() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to add it to DB
            Municipality gen = new Municipality("genoa", "Genova");
            MunicipalityDAO.add(gen);

            // Use DAO to create a new test Object
            Municipality testObject = MunicipalityDAO.getMunicipality("genoa");

            // Verify that the data corresponds.
            Assertions.assertEquals("genoa", testObject.getUsername());
            Assertions.assertEquals("Genova", testObject.getCity());

            // Delete it
            MunicipalityDAO.delete(gen);
        }
    }
    @Test
    public void getMusician() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Musician mao = new Musician("maoo", "Mao", "rock", 4);
            MusicianDAO.add(mao);

            // Use DAO to create a Test Object
            Musician testObject = MusicianDAO.getMusician("maoo");

            // Verify that the data corresponds.
            Assertions.assertEquals("maoo", testObject.getUsername());
            Assertions.assertEquals("Mao", testObject.getName());
            Assertions.assertEquals("rock", testObject.getGenre());
            Assertions.assertEquals(4, testObject.getComponentNumb());

            // Delete it
            MusicianDAO.delete(mao);
        }
    }
    @Test
    public void getOwner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Owner ciccio = new Owner("ciccio", "Giovannissimo");
            OwnerDAO.add(ciccio);

            // Use DAO to create a new Test Object
            Owner testOwner = OwnerDAO.getOwner("ciccio");

            // Verify that the data corresponds.
            Assertions.assertEquals("ciccio", testOwner.getUsername());
            Assertions.assertEquals("Giovannissimo", testOwner.getName());

            // Delete it
            OwnerDAO.delete(ciccio);
        }
    }
    @Test
    public void getPlanner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            Planner molletta = new Planner("molletta", "Malviana");
            PlannerDAO.add(molletta);

            // Use DAO to create a new Test Object
            Planner testPlanner = PlannerDAO.getPlanner("Malviana");

            // Verify that the data corresponds.
            Assertions.assertEquals("Malviana", testPlanner.getUsername());
            Assertions.assertEquals("molletta", testPlanner.getName());

            // Delete it
            PlannerDAO.delete(molletta);
        }
    }
    @Test
    public void getUser() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            User gianni = new User("gianni", "gianni_username");
            UserDAO.add(gianni);

            // Use DAO to create a new Test Object
            User testUser = UserDAO.getUser("gianni_username");

            // Verify that the data corresponds.
            Assertions.assertEquals("gianni_username", testUser.getUsername());

            // Delete data from DB
            UserDAO.delete(gianni);
        }
    }

}
