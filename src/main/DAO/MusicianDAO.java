package main.DAO;

import main.DomainModel.Musician;

import java.sql.*;

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
        findId.close();
        insertMusician.close();
        connection.close();

        // Show result
        System.out.println("New MUSICIAN added successfully!");
    }

    // TODO: implement all the other functions: update, delete, getAll
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
        findId.close();
        deleteMusician.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }
}
