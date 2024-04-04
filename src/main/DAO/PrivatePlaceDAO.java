package main.DAO;

import main.DomainModel.Owner;
import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;

import java.sql.*;
import java.util.ArrayList;

public class PrivatePlaceDAO {
    public static void add (PrivatePlace privatePlace) throws SQLException{
        // First, we need to add the privatePlace as a Place
        PlaceDAO.add(privatePlace);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add PrivatePlace to DataBase
        PreparedStatement insertPrivatePlace = conn.prepareStatement("insert into PrivatePlaces(id, type, owner) values(?, ?, ?)");
        // Add the real values instead of "?"
        insertPrivatePlace.setInt(1, privatePlace.getId());
        insertPrivatePlace.setString(2, privatePlace.getName());
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
        PreparedStatement deletePrivatePlace = conn.prepareStatement("delete from PrivatePlaces where id = ?");
        deletePrivatePlace.setInt(1, privatePlace.getId());

        // Call the Place delete function
        PlaceDAO.delete(privatePlace);

        // Close connections
        deletePrivatePlace.executeUpdate();
        deletePrivatePlace.close();
        conn.close();

        // The result is logged in the PlaceDAO.delete
    }

    public ArrayList<PrivatePlace> getAll() throws SQLException {
        ArrayList<PrivatePlace> places = new ArrayList<PrivatePlace>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select * from privateplace_esteso_intero");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while (resultSet.next()){
            // retrieve data
            String ownerName = resultSet.getString("owner_name");
            String ownerUsername = resultSet.getString("owner_username");
            int ownerId = resultSet.getInt("owner_id");

            int placeId = resultSet.getInt("privateplace_id");
            String name = resultSet.getString("privateplace_name");
            String city = resultSet.getString("privateplace_city");
            String address = resultSet.getString("privateplace_address");
            int capacity = resultSet.getInt("privateplace_capacity");
            boolean indoor = resultSet.getBoolean("privateplace_indoor");
            String type = resultSet.getString("privateplace_type");

            // create the objects
            Owner owner = new Owner(ownerName, ownerUsername);
            owner.setId(ownerId);

            PrivatePlace place = new PrivatePlace(city, name, address, capacity, indoor, PlaceType.valueOf(type), owner);
            place.setId(placeId);
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
