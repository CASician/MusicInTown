package main.DAO;

import main.DomainModel.Municipality;
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

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, owner.getUsername());

        // Use the result to give the same ID to Musician in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO Owners(id, name, place) VALUES (?, ?, ?)");
        insertOwner.setInt(1, resultSet.getInt("id"));

        // Add the real values instead of "?"
        insertOwner.setString(2, owner.getName());
        insertOwner.setString(3, owner.getPlace().getName());
        insertOwner.executeUpdate();

        // Close connection
        findId.close();
        insertOwner.close();
        connection.close();

        // Show result
        System.out.println("New OWNER added successfully!");
    }

    public static void delete(Owner owner) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Find ID in BasicUsers. It is needed to delete the instance in Owner.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, owner.getUsername());

        // Delete Owner from its table with the ID found before
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();
        PreparedStatement deleteOwner = conn.prepareStatement("delete from Owners where id = ?");
        deleteOwner.setInt(1, resultSet.getInt("id"));
        deleteOwner.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(owner);

        // Close connections
        findId.close();
        deleteOwner.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public ArrayList<Owner> getAll() throws SQLException {
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
}
