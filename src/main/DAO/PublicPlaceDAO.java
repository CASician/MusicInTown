package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

import java.sql.*;
import java.util.ArrayList;

public class PublicPlaceDAO {
    public static void add(PublicPlace publicPlace) throws SQLException{
        // First, we need to add our PublicPlace as a Place
        PlaceDAO.add(publicPlace);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add the PublicPlace in its own table
        PreparedStatement insertPublicPlace = conn.prepareStatement("insert into PublicPlaces(id) values (?)");
        // Add the real values instead of "?"
        insertPublicPlace.setInt(1, publicPlace.getId());

        // Close connections
        insertPublicPlace.executeUpdate();
        insertPublicPlace.close();
        conn.close();

        // Show results
        System.out.println("New PUBLIC PLACE added successfully!");
    }

    public static void delete(PublicPlace publicPlace) throws SQLException{
        // Connection to database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete PublicPlace from its table
        PreparedStatement deletePublicPlace = conn.prepareStatement("delete from PublicPlaces where id = ?");
        deletePublicPlace.setInt(1, publicPlace.getId());

        // Call the Place delete function
        PlaceDAO.delete(publicPlace);

        // Close connections
        deletePublicPlace.executeUpdate();
        deletePublicPlace.close();
        conn.close();

        // The result is logged in the PlaceDAO.delete
    }

    public ArrayList<PublicPlace> getAll() throws SQLException {
        ArrayList<PublicPlace> places = new ArrayList<PublicPlace>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select * from publicplace_intero");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while (resultSet.next()){
            // retrieve data
            int placeId = resultSet.getInt("publicplace_id");
            String name = resultSet.getString("publicplace_name");
            String city = resultSet.getString("publicplace_city");
            String address = resultSet.getString("publicplace_address");
            int capacity = resultSet.getInt("publicplace_capacity");
            boolean indoor = resultSet.getBoolean("publicplace_indoor");

            // create the objects
            PublicPlace place = new PublicPlace(name, city, address, capacity, indoor);
            place.setId(placeId);

            // Add to array
            places.add(place);
        }

        // Close connections
        resultSet.close();
        getAll.close();
        connection.close();

        // The end
        return places;
    }
}
