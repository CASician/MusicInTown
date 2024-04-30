package main.DAO;

import main.DomainModel.Event;
import main.DomainModel.Musician;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.sql.*;
import java.util.ArrayList;

public class SubscriptionsDAO {
    public static ArrayList<PublicEvent> getAllPublic_musician(Musician musician) throws SQLException{
        // create the array you return
        ArrayList<PublicEvent> events = new ArrayList<>();
        // Retrieve the data for all events (not optimized but eh)
        ArrayList<PublicEvent> allEvents = PublicEventDAO.getAll();

        // connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from database specifying for what subscriber
        PreparedStatement getAll = conn.prepareStatement("select * from Subscriptions where id_subscriber = ?");
        getAll.setInt(1, musician.getId());
        ResultSet resultSet = getAll.executeQuery();

        // Add the data in the array
        while (resultSet.next()){
            // retrieve data
            int id = resultSet.getInt("id_event");

            // compare to getAll
            for (PublicEvent event: allEvents){
                if (event.getId() == id){
                    // add them to the array
                    events.add(event);
                }
            }
        }

        // Close connections
        resultSet.close();
        getAll.close();
        conn.close();

        // The end
        return events;
    }

    public static ArrayList<PrivateEvent> getAllPrivate_musician(Musician musician) throws SQLException{
        // create the array you return
        ArrayList<PrivateEvent> events = new ArrayList<>();
        // Retrieve the data for all events (not optimized but eh)
        ArrayList<PrivateEvent> allEvents = PrivateEventDAO.getAll();

        // connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from database specifying for what subscriber
        PreparedStatement getAll = conn.prepareStatement("select * from Subscriptions where id_subscriber = ?");
        getAll.setInt(1, musician.getId());
        ResultSet resultSet = getAll.executeQuery();

        // Add the data in the array
        while (resultSet.next()){
            // retrieve data
            int id = resultSet.getInt("id_event");

            // compare to getAll
            for (PrivateEvent event: allEvents){
                if (event.getId() == id){
                    // add them to the array
                    events.add(event);
                }
            }
        }

        // Close connections
        resultSet.close();
        getAll.close();
        conn.close();

        // The end
        return events;
    }

    public static ArrayList<Musician> getSubscribers(Event event) throws SQLException{
        // create the array you return
        ArrayList<Musician> musicians = new ArrayList<>();
        // Retrieve the data for all events (not optimized but eh)
        ArrayList<Musician> allMusicians = MusicianDAO.getAll();

        // connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from database specifying for what event
        PreparedStatement getAll = conn.prepareStatement("select * from Subscriptions where id_event = ?");
        getAll.setInt(1, event.getId());
        ResultSet resultSet = getAll.executeQuery();

        // Add the data in the array
        while (resultSet.next()){
            // retrieve data
            int id = resultSet.getInt("id_subscriber");

            // compare to getAll
            for (Musician musician: allMusicians){
                if (musician.getId() == id){
                    // add them to the array
                    musicians.add(musician);
                }
            }
        }

        // Close connections
        resultSet.close();
        getAll.close();
        conn.close();

        // The end
        return musicians;
    }

    public static void add(Musician musician, Event event) throws SQLException{
        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add event to the table
        PreparedStatement insertEvent = conn.prepareStatement("insert into subscriptions(id_subscriber, id_event) values(?, ?)");
        insertEvent.setInt(1, musician.getId());
        insertEvent.setInt(2, event.getId());
        insertEvent.executeUpdate();

        // Close connections
        insertEvent.close();
        conn.close();

        // Show results
        System.out.println("Subscription registered correctly in the DB! ");
    }

    public static void delete(Musician musician, Event event) throws SQLException{
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete event from the table
        PreparedStatement deleteEvent = conn.prepareStatement("delete from subscriptions where id_event = ? and id_subscriber = ?");
        deleteEvent.setInt(1, event.getId());
        deleteEvent.setInt(2, musician.getId());
        deleteEvent.executeUpdate();

        // Close connections
        deleteEvent.close();
        conn.close();
    }

    public static boolean exists(Musician musician, Event event) throws SQLException{
        boolean found = false;

        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Search event from the table
        PreparedStatement find = conn.prepareStatement("select count(*) from subscriptions where id_event = ? and id_subscriber = ?");
        find.setInt(1, event.getId());
        find.setInt(2, musician.getId());
        ResultSet resultSet = find.executeQuery();

        // Deal with result set
        resultSet.next();
        int result = resultSet.getInt("count");

        // Check if the count == 1
        if (result == 1){
            found = true;
        }

        // Close connections
        find.close();
        conn.close();

        return found;
    }
}
