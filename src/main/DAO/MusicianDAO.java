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

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, musician.getUsername());

        // Use the result to give the same ID to Musician in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertMusician = connection.prepareStatement("INSERT INTO Musicians(id, name, genre, componentNumb) VALUES (?, ?, ?, ?)");
        insertMusician.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        insertMusician.setString(2, musician.getName());
        insertMusician.setString(3, musician.getGenre());
        insertMusician.setInt(4, musician.getComponentNumb());
        insertMusician.executeUpdate();

        // Close connection
        resultSet.close();
        findId.close();
        insertMusician.close();
        connection.close();

        // Show result
        System.out.println("New MUSICIAN added successfully!");
    }

    public static void delete(Musician musician) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Find ID in BasicUsers. It is needed to delete the instance in Musician.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, musician.getUsername());

        // Delete Musician from its table with the ID found before
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();
        PreparedStatement deleteMusician = conn.prepareStatement("delete from Musicians where id = ?");
        deleteMusician.setInt(1, resultSet.getInt("id"));
        deleteMusician.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(musician);

        // Close connections
        resultSet.close();
        findId.close();
        deleteMusician.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public ArrayList<Musician> getAll() throws SQLException {
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
}
