package main.DAO;

import main.DomainModel.Planner;

import java.sql.*;

public class PlannerDAO {
    public static void add (Planner planner) throws SQLException{
        // First, we need to add our Planner as a BasicUser
        BasicUserDAO.add(planner);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE email = ? AND city = ? AND username = ?");
        findId.setString(1, planner.getEmail());
        findId.setString(2, planner.getCity());
        findId.setString(3, planner.getUsername());

        // Use the result to give the same ID to Musician in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertPlanner = connection.prepareStatement("INSERT INTO Planners(id) VALUES (?)");
        insertPlanner.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        // There are none.
        insertPlanner.executeUpdate();

        // Close connection
        findId.close();
        insertPlanner.close();
        connection.close();

        // Show result
        System.out.println("New PLANNER added successfully!");
    }

    // TODO: implement all the other functions: update, delete, getAll

    public static void delete(Planner planner) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Find ID in BasicUsers. It is needed to delete the instance in Planner.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, planner.getUsername());

        // Delete Planner from its table with the ID found before
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();
        PreparedStatement deletePlanner = conn.prepareStatement("delete from Planners where id = ?");
        deletePlanner.setInt(1, resultSet.getInt("id"));
        deletePlanner.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(planner);

        // Close connections
        findId.close();
        deletePlanner.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }
}
