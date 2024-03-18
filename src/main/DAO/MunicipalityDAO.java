package main.DAO;

import main.DomainModel.Municipality;

import java.sql.*;
public class MunicipalityDAO {

    Municipality municipality;

    public static void add (Municipality municipality)  throws SQLException {
        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // First, we need to add our Municipality as a BasicUser
        PreparedStatement insertBasicUser = conn.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(2, municipality.getCity()); 
        insertBasicUser.setString(3, municipality.getUsername());
        insertBasicUser.executeUpdate();

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = conn.prepareStatement("SELECT id FROM BasicUsers WHERE email = ? AND city = ? AND username = ?");
        findId.setString(2, municipality.getCity());
        findId.setString(3, municipality.getUsername());

        // Use the result to give the same ID to Municipality in its own Table.
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        PreparedStatement insertMunicipality = conn.prepareStatement("INSERT INTO Municipalities(id) VALUES(?)");
        insertMunicipality.setInt(1, resultSet.getInt("id"));
        insertMunicipality.executeUpdate();

        //Close connections
        findId.close();
        insertMunicipality.close();
        insertBasicUser.close();
        conn.close();

        // Show results
        System.out.println("New MUNICIPALITY added successfully!");
    }

    public Municipality getMunicipality(String municipality) {
        //Get the municipality from the Database
        this.municipality = new Municipality(1, municipality, "Florence");
        return this.municipality;
    }

    //TODO: implement other functions: update, delete, get(id), getAll

}
