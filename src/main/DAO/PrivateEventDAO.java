package main.DAO;

import main.DomainModel.PrivateEvent;

import java.sql.*;

public class PrivateEventDAO {
    public static void add(PrivateEvent privateEvent) throws SQLException{
        // First, we need to add the PrivateEvent in the Events table
        EventsDAO.add(privateEvent);

        // Connection to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM Events WHERE name = ?");
        findId.setString(1, privateEvent.getName());

        // Use the result to give the same ID to PrivateEvent in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertPrivateEvent = conn.prepareStatement("insert into PrivateEvents(id, place, planner, ownerPlanner) values (?, ?, ?, ?)");
        insertPrivateEvent.setInt(1, resultSet.getInt("id"));

        // Insert real data instead of "?"
        insertPrivateEvent.setString(2, privateEvent.getPlace().getName());
        if (privateEvent.getPlanner() != null) // If there's a Planner, there is no OwnerPlanner and vice-versa
        {
            insertPrivateEvent.setString(3, privateEvent.getPlanner().getUsername());
            insertPrivateEvent.setString(4, null);
        } else {
            insertPrivateEvent.setString(3, null);
            insertPrivateEvent.setString(4, privateEvent.getOwnerPlanner().getUsername());
        }

        // Close connections
        insertPrivateEvent.executeUpdate();
        findId.close();
        insertPrivateEvent.close();
        conn.close();

        // Show results
        System.out.println("New PRIVATE EVENT added successfully!");
    }

    public static void delete(PrivateEvent privateEvent) throws SQLException {
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM Events WHERE name = ?");
        findId.setString(1, privateEvent.getName());
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();

        // Delete PrivateEvent from its table
        PreparedStatement deletePrivateEvent = conn.prepareStatement("delete from PrivateEvents where id = ?");
        deletePrivateEvent.setInt(1, resultSet.getInt("id"));

        // Call the Events delete function
        EventsDAO.delete(privateEvent);

        // Close connections
        findId.close();
        deletePrivateEvent.executeUpdate();
        deletePrivateEvent.close();
        conn.close();

        // The results are logged in the EventsDAO.delete
    }

    // TODO: getAll
}
