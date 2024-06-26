package main.DAO;

import main.DomainModel.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PrivateEventDAO {
    public static void add(PrivateEvent privateEvent) throws SQLException{
        // First, we need to add the PrivateEvent in the Events table
        EventDAO.add(privateEvent);

        // Connection to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add PrivateEvent to DataBase
        PreparedStatement insertPrivateEvent = conn.prepareStatement("insert into PrivateEvents(id, place, planner, ownerPlanner) values (?, ?, ?, ?)");
        // Insert real data instead of "?"
        insertPrivateEvent.setInt(1, privateEvent.getId());
        insertPrivateEvent.setString(2, privateEvent.getPlace().getName());

        if (privateEvent.getPlanner() != null) // If there's a Planner, there is no OwnerPlanner and vice-versa
        {
            insertPrivateEvent.setString(3, privateEvent.getPlanner().getName());
            insertPrivateEvent.setString(4, null);
        } else {
            insertPrivateEvent.setString(3, null);
            insertPrivateEvent.setString(4, privateEvent.getOwnerPlanner().getName());
        }

        // Close connections
        insertPrivateEvent.executeUpdate();
        insertPrivateEvent.close();
        conn.close();

        // Show results
        System.out.println("New PRIVATE EVENT added successfully!");
    }

    public static void delete(PrivateEvent privateEvent) throws SQLException {
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete PrivateEvent from its table
        PreparedStatement deletePrivateEvent = conn.prepareStatement("delete from PrivateEvents where id = ?");
        deletePrivateEvent.setInt(1, privateEvent.getId());

        // Call the Events delete function
        EventDAO.delete(privateEvent);

        // Close connections
        deletePrivateEvent.executeUpdate();
        deletePrivateEvent.close();
        conn.close();

        // The results are logged in the EventsDAO.delete
    }

    public static ArrayList<PrivateEvent> getAll() throws SQLException {
        // Create the array you return
        // PrivateEventsList eventsList = new PrivateEventsList();
        ArrayList<PrivateEvent> privateEvents = new ArrayList<PrivateEvent>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select * from privateevents_esteso_intero");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            // retrieve data from Events
            int privateeventId = resultSet.getInt("privateevent_id");
            String name = resultSet.getString("privateevent_name");
            boolean open = resultSet.getBoolean("privateevent_open");
            java.sql.Date date_to_convert = resultSet.getDate("privateevent_date");
            LocalDate date = date_to_convert.toLocalDate();
            String city = resultSet.getString("privateevent_city");
            String type = resultSet.getString("privateevent_type");
            String duration = resultSet.getString("privateevent_duration");
            boolean accepted = resultSet.getBoolean("privateevent_accepted");

            // from Places
            String placeName = resultSet.getString("privateplace_name");
            int placeId = resultSet.getInt("privateplace_id");
            String placeCity = resultSet.getString("privateplace_city");
            String address = resultSet.getString("privateplace_address");
            int capacity = resultSet.getInt("privateplace_capacity");
            boolean indoor = resultSet.getBoolean("privateplace_indoor");
            String placeType = resultSet.getString("privateplace_type");

            // from Owner and BasicUsers
            int ownerId = resultSet.getInt("owner_id");
            String ownerName = resultSet.getString("owner_name");
            String ownerUsername = resultSet.getString("owner_username");

            // create objects
            Owner owner = new Owner(ownerName, ownerUsername);
            owner.setId(ownerId);

            PrivatePlace place = new PrivatePlace(placeCity, placeName, address, capacity, indoor, PlaceType.valueOf(placeType), owner);
            place.setId(placeId);

            if (resultSet.getString("planner_name") != null) {
                // from Planners and BasicUsers
                int planner_id = resultSet.getInt("planner_id");
                String plannerName = resultSet.getString("planner_name");
                String plannerUsername = resultSet.getString("planner_username");

                Planner planner = new Planner(plannerName, plannerUsername);
                planner.setId(planner_id);

                PrivateEvent event = new PrivateEvent(name, open, date, planner, place, duration, city, type);
                event.setId(privateeventId); // ID is not assigned in the constructor.
                event.setAccepted(accepted);
                // The end
                privateEvents.add(event);
            } else {
                PrivateEvent event = new PrivateEvent(name, open, date, owner, place, duration, city, type);
                event.setId(privateeventId);
                event.setAccepted(accepted);

                // The end
                privateEvents.add(event);
            }
        }

        // Close connections
        resultSet.close();
        getAll.close();
        connection.close();

        // return eventsByExternals;
        return privateEvents;
    }
}
