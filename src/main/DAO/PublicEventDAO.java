package main.DAO;

import main.DomainModel.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PublicEventDAO {
    public static void add(PublicEvent publicEvent) throws SQLException {
        // First, we need to add the PublicEvent in the Events table
        EventDAO.add(publicEvent);

        // Connection to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add PublicEvent to DataBase
        PreparedStatement insertPublicEvent = conn.prepareStatement("insert into PublicEvents(id, place, planner) values (?, ?, ?)");

        // Insert real data instead of "?"
        insertPublicEvent.setInt(1, publicEvent.getId());
        insertPublicEvent.setString(2, publicEvent.getPlace().getName());
        insertPublicEvent.setString(3, publicEvent.getPlanner().getUsername());

        // Close connections
        insertPublicEvent.executeUpdate();
        insertPublicEvent.close();
        conn.close();

        // Show results
        System.out.println("New PUBLIC EVENT added successfully! ");
    }

    public static void delete(PublicEvent publicEvent) throws SQLException{
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete PublicEvent from its table
        PreparedStatement deletePublicEvent = conn.prepareStatement("delete from PublicEvents where id = ?");
        deletePublicEvent.setInt(1, publicEvent.getId());

        // Call the Events delete function
        EventDAO.delete(publicEvent);

        // Close connections
        deletePublicEvent.executeUpdate();
        deletePublicEvent.close();
        conn.close();

        // The results are logged in the EventsDAO.delete
    }

    public static ArrayList<PublicEvent> getAll() throws SQLException {
        // Create the array you return
        ArrayList<PublicEvent> events = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select * from publicevents_esteso_intero");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            // retrieve data from Events
            int id = resultSet.getInt("publicevent_id");
            String name = resultSet.getString("publicevent_name");
            boolean open = resultSet.getBoolean("publicevent_open");
            java.sql.Date date_to_convert = resultSet.getDate("publicevent_date");
            LocalDate date = date_to_convert.toLocalDate();
            String city = resultSet.getString("publicevent_city");
            String type = resultSet.getString("publicevent_type");
            String duration = resultSet.getString("publicevent_duration");
            boolean accepted = resultSet.getBoolean("publicevent_accepted");

            // from Places
            String placeName = resultSet.getString("publicplace_name");
            int placeId = resultSet.getInt("publicplace_id");
            String placeCity = resultSet.getString("publicplace_city");
            String address = resultSet.getString("publicplace_address");
            int capacity = resultSet.getInt("publicplace_capacity");
            boolean indoor = resultSet.getBoolean("publicplace_indoor");

            // from Planners and BasicUsers
            int planner_id = resultSet.getInt("planner_id");
            String plannerName = resultSet.getString("planner_name");
            String plannerUsername = resultSet.getString("planner_username");

            // create objects
            PublicPlace place = new PublicPlace(placeName, placeCity, address, capacity, indoor);
            place.setId(placeId);
            Planner planner = new Planner(plannerName, plannerUsername);
            planner.setId(planner_id);
            PublicEvent event = new PublicEvent(name, open, date, planner, place, duration, city, type);
            event.setId(id); // ID is not assigned in the constructor.
            event.setAccepted(accepted);

            // Add to the array
            events.add(event);
        }

        // Close connections
        resultSet.close();
        getAll.close();
        connection.close();

        // The end
        return events;
    }
}
