package main.DAO;

import main.DomainModel.Planner;

import java.sql.*;

public class PlannerDAO {
    public static void add (Planner planner) throws SQLException{
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // First, we need to add our Planner as a BasicUser
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(1, planner.getEmail());
        insertBasicUser.setString(2, planner.getCity());
        insertBasicUser.setString(3, planner.getUsername());
        insertBasicUser.executeUpdate();

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
        insertBasicUser.close();
        insertPlanner.close();
        connection.close();

        // Show result
        System.out.println("New PLANNER added successfully!");
    }

    // TODO: implement all the other functions: update, delete, getAll
}
