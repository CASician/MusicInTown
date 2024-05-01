package main.DAO;

import main.DomainModel.Municipality;
import main.DomainModel.Musician;
import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;

import java.sql.*;
import java.util.ArrayList;

public class OwnerDAO {
    public static void add (Owner owner) throws SQLException {
        // First, we need to add our Owner as a BasicUser
        BasicUserDAO.add(owner);

        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use the result to give the same ID to Musician in its own Table.
        PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO Owners(id, name, place) VALUES (?, ?, ?)");
        // Add the real values instead of "?"
        insertOwner.setInt(1, owner.getId());
        insertOwner.setString(2, owner.getName());

        if (owner.getPlace() != null) {
            insertOwner.setString(3, owner.getPlace().getName());
        } else {
            insertOwner.setString(3, null);
        }
        insertOwner.executeUpdate();

        // Close connection
        insertOwner.close();
        connection.close();

        // Show result
        System.out.println("New OWNER added successfully!");
    }

    public static void delete(Owner owner) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete Owner from its table
        PreparedStatement deleteOwner = conn.prepareStatement("delete from Owners where id = ?");
        deleteOwner.setInt(1, owner.getId());
        deleteOwner.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(owner);

        // Close connections
        deleteOwner.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public static ArrayList<Owner> getAll() throws SQLException {
        // Create the array you return
        ArrayList<Owner> users = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select o.id, o.name, o.place, BU.username from Owners O join BasicUsers BU on O.id = BU.id");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            // Add data
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");

            // to the array
            Owner user = new Owner(name, username);
            user.setId(id); // ID is not assigned in the constructor.
            users.add(user);
        }

        // Close connections
        resultSet.close();
        getAll.close();
        connection.close();

        // The end
        return users;
    }

    public static Owner getOwner(String username) throws SQLException{
        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getOwner = connection.prepareStatement("select o.id, o.name, o.place, BU.username from Owners O join BasicUsers BU on O.id = BU.id where BU.username = ?");
        getOwner.setString(1, username);
        ResultSet resultSet = getOwner.executeQuery();

        // Create the object with the retrieved data
        resultSet.next();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        Owner owner = new Owner(username, name);

        if (resultSet.getString("place") != null) {
            String privatePlaceName = resultSet.getString("place");
            PrivatePlace privatePlace = PrivatePlaceDAO.getPrivatePlace(privatePlaceName);
            owner.setPrivatePlace(privatePlace);
        }
        owner.setId(id);
        owner.setEventsToBeAccepted(EventsToBeAcceptedDAO.getAll_owner(owner));

        // Close connections
        resultSet.close();
        getOwner.close();
        connection.close();

        // The end
        return owner;
    }
}
