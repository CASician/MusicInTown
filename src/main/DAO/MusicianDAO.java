package main.DAO;

import main.DomainModel.Musician;

import java.sql.*;

public class MusicianDAO {
    public static void add (Musician musician) throws SQLException {
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // First, we need to add our Musician as a BasicUser
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(1, musician.getEmail());
        insertBasicUser.setString(2, musician.getCity());
        insertBasicUser.setString(3, musician.getUsername());
        insertBasicUser.executeUpdate();

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE email = ? AND city = ? AND username = ?");
        findId.setString(1, musician.getEmail());
        findId.setString(2, musician.getCity());
        findId.setString(3, musician.getUsername());

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
        insertBasicUser.close();
        insertMusician.close();
        connection.close();

        // Show result
        System.out.println("New MUSICIAN added successfully!");
    }

    // TODO: implement all the other functions: update, delete, getAll
}
