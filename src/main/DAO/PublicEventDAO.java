package main.DAO;

import main.DomainModel.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PublicEventDAO {
    public static void add(PublicEvent publicEvent) throws SQLException{
        // First, we need to add the PublicEvent in the Events table
        EventDAO.add(publicEvent);

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
        resultSet.close();
        findId.close();
        insertPublicEvent.executeUpdate();
        insertPublicEvent.close();
        conn.close();

        // Show results
        System.out.println("New PUBLIC EVENT added successfully! ");
    }

    public static void delete(PublicEvent publicEvent) throws SQLException{
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM Events WHERE name = ?");
        findId.setString(1, publicEvent.getName());
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();

        // Delete PublicEvent from its table
        PreparedStatement deletePublicEvent = conn.prepareStatement("delete from PublicEvents where id = ?");
        deletePublicEvent.setInt(1, resultSet.getInt("id"));

        // Call the Events delete function
        EventDAO.delete(publicEvent);

        // Close connections
        resultSet.close();
        findId.close();
        deletePublicEvent.executeUpdate();
        deletePublicEvent.close();
        conn.close();

        // The results are logged in the EventsDAO.delete
    }

    public ArrayList<PublicEvent> getAll() throws SQLException {
        // Create the array you return
        ArrayList<PublicEvent> events = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement(
                "select \n" +
                        "pe.id as event_id, e.name as event_name, e.open, e.date, e.city as city_of_event, e.type, e.duration, e.accepted,\n" +
                        "pe.place as place_name, pp.id as place_id, pp.city as city_of_place, pp.address, pp.capacity, pp.indoor,\n" +
                        "pe.planner as planner_name, pl.id as planner_id,\n" +
                        "bu.username as planner_username\n" +
                        "from publicevents pe \n" +
                        "join events e on (pe.id = e.id)\n" +
                        "join places pp on (pp.name = pe.place)\n" +
                        "join planners pl on (pl.name = pe.planner)\n" +
                        "join basicusers bu on (pl.id = bu.id)"
        );
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            // retrieve data from Events
            int id = resultSet.getInt("event_id");
            String name = resultSet.getString("event_name");
            boolean open = resultSet.getBoolean("open");
            Date date_to_convert = resultSet.getDate("date");
            LocalDate date = date_to_convert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String city = resultSet.getString("city_of_event");
            String type = resultSet.getString("type");
            String duration = resultSet.getString("duration");
            boolean accepted = resultSet.getBoolean("accepted");

            // from Places
            String placeName = resultSet.getString("place_name");
            int placeId = resultSet.getInt("place_id");
            String placeCity = resultSet.getString("city_of_place");
            String address = resultSet.getString("address");
            int capacity = resultSet.getInt("capacity");
            boolean indoor = resultSet.getBoolean("indoor");

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
