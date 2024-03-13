package main.DAO;

import main.DomainModel.Municipality;

import java.sql.*;

public class MunicipalityDAO {

    public static void add (Municipality municipality)  throws SQLException {
        // First, we need to add our Municipality as a BasicUser
        BasicUserDAO.add(municipality);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, municipality.getUsername());

        // Use the result to give the same ID to Municipality in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertMunicipality = conn.prepareStatement("INSERT INTO Municipalities(id) VALUES(?)");
        insertMunicipality.setInt(1, resultSet.getInt("id"));
        insertMunicipality.executeUpdate();

        //Close connections
        findId.close();
        insertMunicipality.close();
        conn.close();

        // Show results
        System.out.println("New MUNICIPALITY added successfully!");
    }

    //TODO: implement other functions: update, delete, get(id), getAll

    public static void delete(Municipality municipality) throws SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Find ID in BasicUsers. It is needed to delete the instance in Municipality.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, municipality.getUsername());

        // Delete Municipality from its table with the ID found before
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();
        PreparedStatement deleteMunicipality = conn.prepareStatement("delete from Municipalities where id = ?");
        deleteMunicipality.setInt(1, resultSet.getInt("id"));
        deleteMunicipality.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(municipality);

        // Close connections
        findId.close();
        deleteMunicipality.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }
}
