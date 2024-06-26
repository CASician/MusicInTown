package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.Planner;
import main.DomainModel.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public static void add (User user) throws SQLException {
        // First, we need to add our User as a BasicUser
        BasicUserDAO.add(user);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add User to DataBase
        PreparedStatement insertUser = connection.prepareStatement("INSERT INTO \"Users\"(id, name) VALUES (?, ?)");

        // Add the real values instead of "?"
        insertUser.setInt(1, user.getId());
        insertUser.setString(2, user.getName());
        insertUser.executeUpdate();

        // Close connection
        insertUser.close();
        connection.close();

        // Show result
        System.out.println("New USER added successfully!");
    }

    public static void delete(User user) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete User from its table
        PreparedStatement deleteUser = conn.prepareStatement("delete from \"Users\" where id = ?");
        deleteUser.setInt(1, user.getId());
        deleteUser.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(user);

        // Close connections
        deleteUser.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public static ArrayList<User> getAll () throws SQLException {
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

    public static User getUser(String username) throws SQLException{
        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getUser = connection.prepareStatement("select u.id, u.name, BU.username from \"Users\" u join BasicUsers BU on u.id = BU.id where BU.username = ?");
        getUser.setString(1, username);
        ResultSet resultSet = getUser.executeQuery();

        // Create the object with the retrieved data
        resultSet.next();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        User user = new User(name, username);
        user.setId(id);

        // Close connections
        resultSet.close();
        getUser.close();
        connection.close();

        // The end
        return user;
    }
}
