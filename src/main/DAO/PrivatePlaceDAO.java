package main.DAO;

import main.DomainModel.Place;
import main.DomainModel.PrivatePlace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrivatePlacesDAO {
    public static void add (PrivatePlace privatePlace) throws SQLException{
        // First, we need to add the privatePlace as a Place
        PlacesDAO.add(privatePlace);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add the PrivatePlace in its own table
        PreparedStatement insertPrivatePlace = conn.prepareStatement("insert into PrivatePlaces(name, type) values(?, ?)");
        // Add the real values instead of "?"
        insertPrivatePlace.setString(1, privatePlace.getName());
        insertPrivatePlace.setString(2, String.valueOf(privatePlace.getType()));

        // Close connections
        insertPrivatePlace.executeUpdate();
        insertPrivatePlace.close();
        conn.close();

        // Show results
        System.out.println("New PRIVATE PLACE added successfully!");
    }
    public static void delete(PrivatePlace privatePlace) throws SQLException{
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete PrivatePlace from its table
        PreparedStatement deletePrivatePlace = conn.prepareStatement("delete from PrivatePlaces where name = ?");
        deletePrivatePlace.setString(1, privatePlace.getName());

        // Call the Place delete function
        PlacesDAO.delete(privatePlace);

        // Close connections
        deletePrivatePlace.executeUpdate();
        deletePrivatePlace.close();
        conn.close();

        // The result is logged in the PlaceDAO.delete
    }
}
