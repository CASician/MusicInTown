package main.DAO;

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
}
