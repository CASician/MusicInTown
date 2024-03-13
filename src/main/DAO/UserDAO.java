package main.DAO;

import main.DomainModel.User;

import java.sql.*;

public class UserDAO {
    public static void add (User user) throws SQLException {
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // First, we need to add our User as a BasicUser
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(1, user.getEmail());
        insertBasicUser.setString(2, user.getCity());
        insertBasicUser.setString(3, user.getUsername());
        insertBasicUser.executeUpdate();

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE email = ? AND city = ? AND username = ?");
        findId.setString(1, user.getEmail());
        findId.setString(2, user.getCity());
        findId.setString(3, user.getUsername());

        // Use the result to give the same ID to Musician in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertUser = connection.prepareStatement("INSERT INTO \"Users\"(id) VALUES (?)");
        insertUser.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        // There are none
        insertUser.executeUpdate();

        // Close connection
        findId.close();
        insertBasicUser.close();
        insertUser.close();
        connection.close();

        // Show result
        System.out.println("New USER added successfully!");
    }

    //TODO: implement all the other functions: update, delete, getAll
}
