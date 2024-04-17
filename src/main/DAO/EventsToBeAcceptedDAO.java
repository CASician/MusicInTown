package main.DAO;

import main.DomainModel.*;

import javax.swing.text.html.HTMLDocument;
import java.sql.*;
import java.util.ArrayList;

public class EventsToBeAcceptedDAO {
    public static ArrayList<PublicEvent> getAll_municipality(Municipality municipality) throws SQLException{
        // create the array you return
        ArrayList<PublicEvent> events = new ArrayList<>();
        // Retrieve the data for all events (not optimized but eh)
        ArrayList<PublicEvent> allEvents = PublicEventDAO.getAll();

        // connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from database specifying for what controller
        PreparedStatement getAll = conn.prepareStatement("select * from eventstobeaccepted where id_controller = ?");
        getAll.setInt(1, municipality.getId());
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

    public static ArrayList<PrivateEvent> getAll_owner(Owner owner) throws SQLException {
        // create the array you return
        ArrayList<PrivateEvent> events = new ArrayList<>();
        // Retrieve the data for all events (not optimized but eh)
        ArrayList<PrivateEvent> allEvents = PrivateEventDAO.getAll();

        // connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from database specifying for what controller
        PreparedStatement getAll = conn.prepareStatement("select * from eventstobeaccepted where id_controller = ?");
        getAll.setInt(1, owner.getId());
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

    public static void delete(int id) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete event from the table
        PreparedStatement deleteEvent = conn.prepareStatement("delete from eventstobeaccepted where id_event = ?");
        deleteEvent.setInt(1, id);
        deleteEvent.executeUpdate();

        // Modify the attribute Accepted to TRUE
        PreparedStatement modify = conn.prepareStatement("update events set accepted = true where id = ?");
        modify.setInt(1, id);
        modify.executeUpdate();

        // Close connections
        deleteEvent.close();
        modify.close();
        conn.close();
    }
    public static void add(BasicUser basicUser, Event event) throws SQLException{
        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add event to the table
        PreparedStatement insertEvent = conn.prepareStatement("insert into eventstobeaccepted(id_controller, id_event) values(?, ?)");
        insertEvent.setInt(1, basicUser.getId());
        insertEvent.setInt(2, event.getId());
        insertEvent.executeUpdate();

        // Close connections
        insertEvent.close();
        conn.close();

        // Show results
        System.out.println("Event successfully added to the queue! ");
    }
}
