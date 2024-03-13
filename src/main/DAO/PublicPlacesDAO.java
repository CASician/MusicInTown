package main.DAO;

import main.DomainModel.PublicPlace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PublicPlacesDAO {
    public static void add(PublicPlace publicPlace) throws SQLException{
        // First, we need to add our PublicPlace as a Place
        PlacesDAO.add(publicPlace);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add the PublicPlace in its own table
        PreparedStatement insertPublicPlace = conn.prepareStatement("insert into PublicPlaces(name) values (?)");
        // Add the real values instead of "?"
        insertPublicPlace.setString(1, publicPlace.getName());

        // Close connections
        insertPublicPlace.executeUpdate();
        insertPublicPlace.close();
        conn.close();

        // Show results
        System.out.println("New PUBLIC PLACE added successfully!");
    }

    // TODO: implement all the other functions: update, delete, getAll
}
