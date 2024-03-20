package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public static void add (User user) throws SQLException {
        // First, we need to add our User as a BasicUser
        BasicUserDAO.add(user);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, user.getUsername());

        // Use the result to give the same ID to User in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertUser = connection.prepareStatement("INSERT INTO \"Users\"(id) VALUES (?)");
        insertUser.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        // There are none
        insertUser.executeUpdate();

        // Close connection
        resultSet.close();
        findId.close();
        insertUser.close();
        connection.close();

        // Show result
        System.out.println("New USER added successfully!");
    }

    public static void delete(User user) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Find ID in BasicUsers. It is needed to delete the instance in Users.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, user.getUsername());

        // Delete User from its table with the ID found before
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();
        PreparedStatement deleteUser = conn.prepareStatement("delete from \"Users\" where id = ?");
        deleteUser.setInt(1, resultSet.getInt("id"));
        deleteUser.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(user);

        // Close connections
        resultSet.close();
        findId.close();
        deleteUser.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public ArrayList<User> getAll () throws SQLException {
        // Create the array you return
        ArrayList<User> users = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select u.id, u.name, BU.username from \"Users\" u join BasicUsers BU on u.id = BU.id");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");

            // to the array
            User user = new User(name, username);
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
