package main.DAO;

import java.sql.*;

import main.BusinessLogic.PlacesController;
import main.DomainModel.Event;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
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

        // Close connections
        insertEvent.executeUpdate();
        insertEvent.close();
        conn.close();
    };

    //TODO: implementare le altre DAO: update, delete, getAll
}
