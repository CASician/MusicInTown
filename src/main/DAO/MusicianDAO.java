package main.DAO;

import main.DomainModel.Municipality;
import main.DomainModel.Musician;

import java.sql.*;
import java.util.ArrayList;

public class MusicianDAO {
    public static void add (Musician musician) throws SQLException {
        // First, we need to add our Musician as a BasicUser
        BasicUserDAO.add(musician);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add Musician to DataBase
        PreparedStatement insertMusician = connection.prepareStatement("INSERT INTO Musicians(id, name, genre, componentNumb) VALUES (?, ?, ?, ?)");
        // Add the real values instead of "?"
        insertMusician.setInt(1, musician.getId());
        insertMusician.setString(2, musician.getName());
        insertMusician.setString(3, musician.getGenre());
        insertMusician.setInt(4, musician.getComponentNumb());
        insertMusician.executeUpdate();

        // Close connection
        insertMusician.close();
        connection.close();

        // Show result
        System.out.println("New MUSICIAN added successfully!");
    }

    public static void delete(Musician musician) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete Musician from its table
        PreparedStatement deleteMusician = conn.prepareStatement("delete from Musicians where id = ?");
        deleteMusician.setInt(1, musician.getId());
        deleteMusician.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(musician);

        // Close connections
        deleteMusician.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public static ArrayList<Musician> getAll() throws SQLException {
        // Create the array you return
        ArrayList<Musician> users = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select m.id, m.name, m.genre, m.componentNumb, BU.username from Musicians M join BasicUsers BU on M.id = BU.id");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String genre = resultSet.getString("genre");
            int compNr = resultSet.getInt("componentnumb");
            String username = resultSet.getString("username");

            // in the array
            Musician user = new Musician(name, genre, username, compNr);
            user.setId(id); // ID is not assigned in the constructor.
            users.add(user);
        }

        // Close connections
        resultSet.close();
        getAll.close();
        connection.close();

        // The end
        return users;
    }

    public static Musician getMusician(String username) throws SQLException{
        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);
        Musician musician = null;

        try {
            // Retrieve the data from DataBase
            PreparedStatement getMusician = connection.prepareStatement("select m.id, m.name, m.genre, m.componentNumb, BU.username from Musicians M join BasicUsers BU on M.id = BU.id where BU.username = ?");
            getMusician.setString(1, username);
            ResultSet resultSet = getMusician.executeQuery();

            // Create the object with the retrieved data
            if (!resultSet.next()) {
                throw new Exception("Utente non trovato");
            }
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String genre = resultSet.getString("genre");
            int numb = resultSet.getInt("componentNumb");

            musician = new Musician(username, name, genre, numb);
            musician.setId(id);

            // Close connections
            resultSet.close();
            getMusician.close();
        } catch (Exception e) {
            connection.close();
            System.out.println(e.getMessage());
        }
        connection.close();

        // The end
        return musician;
    }
}
