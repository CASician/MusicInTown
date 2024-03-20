package main.DAO;

import java.sql.*;
import java.util.ArrayList;

import main.DomainModel.Event;
import main.DomainModel.Owner;

public class EventDAO {
    //DAO
    public static void add(Event event) throws SQLException {
        // This function will be called by my children, no need to log the results.
        //Connection to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add the Event in Database
        PreparedStatement insertEvent = conn.prepareStatement("INSERT INTO Events(id, name, open, date, city, type, duration) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");

        // Insert real data instead of the "?"
        insertEvent.setString(1, event.getName());
        insertEvent.setBoolean(2, event.isOpen());
        insertEvent.setDate(3, Date.valueOf(event.getDate()));
        insertEvent.setString(4, event.getCity());
        insertEvent.setString(5, event.getType());
        insertEvent.setString(6, event.getDuration());

        // TODO : assign here the ID to event.id
        // this way in subclasses can be simply used getID

        // Close connections
        insertEvent.executeUpdate();
        insertEvent.close();
        conn.close();
    }

    public static void delete(Event event) throws SQLException{
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use query to delete the instance
        PreparedStatement deleteEvent = conn.prepareStatement("delete from Events where name = ?");

        // Add the real value instead of "?"
        deleteEvent.setString(1, event.getName());

        // Close connections
        deleteEvent.executeUpdate();
        deleteEvent.close();
        conn.close();

        // Show results
        System.out.println(event.getName() + " has been deleted from DataBase");
    }
}
