package main.DAO;

import main.DomainModel.PublicEvent;

import java.sql.*;

public class PublicEventsDAO {
    public static void add(PublicEvent publicEvent) throws SQLException{
        // First, we need to add the PublicEvent in the Events table
        EventsDAO.add(publicEvent);

        // Connection to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM Events WHERE name = ?");
        findId.setString(1, publicEvent.getName());

        // Use the result to give the same ID to PrivateEvent in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertPublicEvent = conn.prepareStatement("insert into PublicEvents(id, place, planner) values (?, ?, ?)");
        insertPublicEvent.setInt(1, resultSet.getInt("id"));

        // Insert real data instead of "?"
        insertPublicEvent.setString(2, publicEvent.getPlace().getName());
        insertPublicEvent.setString(3, publicEvent.getPlanner().getUsername());

        // Close connections
        findId.close();
        insertPublicEvent.executeUpdate();
        insertPublicEvent.close();
        conn.close();

        // Show results
        System.out.println("New PUBLIC EVENT added successfully! ");
    }

    // TODO implement the other functions: update, delete, getAll
}
