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
    public static void delete(PublicPlace publicPlace) throws SQLException{
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete PublicPlace from its table
        PreparedStatement deletePublicPlace = conn.prepareStatement("delete from PublicPlaces where name = ?");
        deletePublicPlace.setString(1, publicPlace.getName());

        // Call the Place delete function
        PlacesDAO.delete(publicPlace);

        // Close connections
        deletePublicPlace.executeUpdate();
        deletePublicPlace.close();
        conn.close();

        // The result is logged in the PlaceDAO.delete
    }
}
