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

    User gianni = new User("gianni", "gianni_username");
    Municipality gen = new Municipality("genoa", "Genova");
    Musician mao = new Musician("maoo", "Mao", "rock", 4);
    Owner ciccio = new Owner("ciccio", "Giovannissimo");
    Planner molletta = new Planner("molletta", "Malviana");

    public UsersDAO() throws SQLException {
    }

    @BeforeAll
    public static void setUpDB() throws Exception{

    }



    // ---------------------------- ADD USERS ----------------------------
    @Test
    public void addBasicUser() throws Exception {
        try (Statement st = conn.createStatement()) {
            // Use DAO
            BasicUserDAO.add(gianni);

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
    public void addMunicipality() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            MunicipalityDAO.add(gen);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from municipality_intero where municipality_username=?");
            getUser.setString(1, "genoa");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("genoa", rs.getString("municipality_username"));
            Assertions.assertEquals("Genova", rs.getString("municipality_city") );
        }
    }

    @Test
    public void addMusician() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
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
        }
    }

    @Test
    public void addOwner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            OwnerDAO.add(ciccio);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from owners natural join basicusers where name=?");
            getUser.setString(1, "Giovannissimo");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("ciccio", rs.getString("username"));
            Assertions.assertEquals("Giovannissimo", rs.getString("name"));
        }
    }

    @Test
    public void addPlanner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO
            PlannerDAO.add(molletta);

            // Use a query to check the data inside the DB
            PreparedStatement getUser = conn.prepareStatement("select * from planners natural join basicusers where name=?");
            getUser.setString(1, "molletta");
            ResultSet rs = getUser.executeQuery();
            rs.next();

            // Verify that the data corresponds.
            Assertions.assertEquals("Malviana", rs.getString("username"));
            Assertions.assertEquals("molletta", rs.getString("name"));
        }
    }
    // ---------------------------- DELETE ----------------------------
    @Test
    public void deleteBasicUser() throws Exception{
        // Delete from DB
        Assertions.assertDoesNotThrow(() -> BasicUserDAO.delete(gianni));
    }
    @Test
    public void deleteMunicipality() throws Exception{
        Assertions.assertDoesNotThrow(() -> MunicipalityDAO.delete(gen));
    }
    @Test
    public void deleteMusician() throws Exception{
        Assertions.assertDoesNotThrow(() -> MusicianDAO.delete(mao));
    }
    @Test
    public void deleteOwner() throws Exception{
        Assertions.assertDoesNotThrow(() -> OwnerDAO.delete(ciccio));
    }
    @Test
    public void deletePlanner() throws Exception{
        Assertions.assertDoesNotThrow(() -> PlannerDAO.delete(molletta));
    }
    // ---------------------------- GETTERS ----------------------------
    @Test
    public void getMunicipality() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to create a new test Object
            Municipality testObject = MunicipalityDAO.getMunicipality("florence");

            // Verify that the data corresponds.
            Assertions.assertEquals("florence", testObject.getUsername());
            Assertions.assertEquals("Firenze", testObject.getCity());
        }
    }
    @Test
    public void getMusician() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to create a Test Object
            Musician testObject = MusicianDAO.getMusician("paba");

            // Verify that the data corresponds.
            Assertions.assertEquals("paba", testObject.getUsername());
            Assertions.assertEquals("Paolo", testObject.getName());
            Assertions.assertEquals("Classic", testObject.getGenre());
            Assertions.assertEquals(1, testObject.getComponentNumb());
        }
    }
    @Test
    public void getOwner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to create a new Test Object
            Owner testOwner = OwnerDAO.getOwner("dante");

            // Verify that the data corresponds.
            Assertions.assertEquals("dante", testOwner.getUsername());
            Assertions.assertEquals("Dante Alighieri", testOwner.getName());
        }
    }
    @Test
    public void getPlanner() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to create a new Test Object
            Planner testPlanner = PlannerDAO.getPlanner("cris");

            // Verify that the data corresponds.
            Assertions.assertEquals("cris", testPlanner.getUsername());
            Assertions.assertEquals("Cristian", testPlanner.getName());
        }
    }
    @Test
    public void getUser() throws Exception{
        try (Statement st = conn.createStatement()) {
            // Use DAO to create a new Test Object
            User testUser = UserDAO.getUser("tizio");

            // Verify that the data corresponds.
            Assertions.assertEquals("tizio", testUser.getUsername());
        }
    }
}
