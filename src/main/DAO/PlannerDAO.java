package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.Planner;

import java.sql.*;
import java.util.ArrayList;

public class PlannerDAO {
    public static void add (Planner planner) throws SQLException{
        // First, we need to add our Planner as a BasicUser
        BasicUserDAO.add(planner);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add Planner to DataBase
        PreparedStatement insertPlanner = connection.prepareStatement("INSERT INTO Planners(id, name) VALUES (?, ?)");

        // Add the real values instead of "?"
        insertPlanner.setInt(1, planner.getId());
        insertPlanner.setString(2, planner.getName());
        insertPlanner.executeUpdate();

        // Close connection
        insertPlanner.close();
        connection.close();

        // Show result
        System.out.println("New PLANNER added successfully!");
    }

    public static void delete(Planner planner) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete Planner from its table
        PreparedStatement deletePlanner = conn.prepareStatement("delete from Planners where id = ?");
        deletePlanner.setInt(1, planner.getId());
        deletePlanner.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(planner);

        // Close connections
        deletePlanner.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public ArrayList<Planner> getAll() throws SQLException {
        // Create the array you return
        ArrayList<Planner> users = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select p.id, p.name, BU.username from Planners p join BasicUsers BU on p.id = BU.id");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");

            // to the array
            Planner user = new Planner(name, username);
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
