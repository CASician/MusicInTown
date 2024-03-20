package main.DAO;

import main.DomainModel.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceDAO {
    public static void add(Place place) throws SQLException{
        // This function will be called by my children, no need to log the results.
        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add Place in its table
        PreparedStatement insertPlace = conn.prepareStatement("insert into Places(id, name, city, address, capacity, indoor) values (DEFAULT, ?, ?, ?, ?, ?)");

        //Add the real values instead of "?"
        insertPlace.setString(1, place.getName());
        insertPlace.setString(2, place.getCity());
        insertPlace.setString(3, place.getAddress());
        insertPlace.setInt(4, place.getCapacity());
        insertPlace.setBoolean(5, place.isIndoor());

        // Close connections
        insertPlace.executeUpdate();
        insertPlace.close();
        conn.close();
    }

    public static void delete(Place place) throws SQLException{
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use query to delete the instance
        PreparedStatement deletePlace = conn.prepareStatement("delete from Places where name = ?");

        // Add the real value instead of "?"
        deletePlace.setString(1, place.getName());

        // Close connections
        deletePlace.executeUpdate();
        deletePlace.close();
        conn.close();

        // Show results
        System.out.println(place.getName() + " has been deleted from DataBase");
    }
}
