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

        // Use the result to give the same ID to Musician in its own Table.
        PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO Owners(id, name, place) VALUES (?, ?, ?)");
        // Add the real values instead of "?"
        insertOwner.setInt(1, owner.getId());
        insertOwner.setString(2, owner.getName());
        insertOwner.setString(3, owner.getPlace().getName());
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
