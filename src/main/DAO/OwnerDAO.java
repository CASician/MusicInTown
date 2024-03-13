package main.DAO;

import main.BusinessLogic.PlacesController;
import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;

import java.sql.*;

public class OwnerDAO {
    public static void add (Owner owner) throws SQLException {
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // First, we need to add our Owner as a BasicUser
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(1, owner.getEmail());
        insertBasicUser.setString(2, owner.getCity());
        insertBasicUser.setString(3, owner.getUsername());
        insertBasicUser.executeUpdate();

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE email = ? AND city = ? AND username = ?");
        findId.setString(1, owner.getEmail());
        findId.setString(2, owner.getCity());
        findId.setString(3, owner.getUsername());

        // Use the result to give the same ID to Musician in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO Owners(id, name, place) VALUES (?, ?, ?)");
        insertOwner.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        insertOwner.setString(2, owner.getName());
        insertOwner.setString(3, owner.getPlaceName());
        insertOwner.executeUpdate();

        // Close connection
        findId.close();
        insertBasicUser.close();
        insertOwner.close();
        connection.close();

        // Show result
        System.out.println("New OWNER added successfully!");
    }

    //TODO: implement all the other functions: update, delete, getAll
}
