package main.DAO;

import main.DomainModel.BasicUser;
import main.DomainModel.Municipality;

import java.sql.*;
import java.util.ArrayList;

public class MunicipalityDAO {

    public static void add (Municipality municipality)  throws SQLException {
        // First, we need to add our Municipality as a BasicUser
        BasicUserDAO.add(municipality);

        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Add Municipality to DataBase
        PreparedStatement insertMunicipality = conn.prepareStatement("INSERT INTO Municipalities(id, city) VALUES(?, ?)");
        insertMunicipality.setInt(1, municipality.getId());
        insertMunicipality.setString(2, municipality.getCity());
        insertMunicipality.executeUpdate();

        //Close connections
        insertMunicipality.close();
        conn.close();

        // Show results
        System.out.println("New MUNICIPALITY added successfully!");
    }

    public static void delete(Municipality municipality) throws  SQLException {
        // Connection to DataBase
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Delete Municipality from its table
        PreparedStatement deleteMunicipality = conn.prepareStatement("delete from Municipalities where id = ?");
        deleteMunicipality.setInt(1, municipality.getId());
        deleteMunicipality.executeUpdate();

        // Call the BasicUser delete function
        BasicUserDAO.delete(municipality);

        // Close connections
        deleteMunicipality.close();
        conn.close();

        // The result is logged in the BasicUserDAO.delete
    }

    public static ArrayList<Municipality> getAll() throws SQLException{
        // Create the array you return
        ArrayList<Municipality> users = new ArrayList<>();

        // Connect to DataBase
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Retrieve the data from DataBase
        PreparedStatement getAll = connection.prepareStatement("select m.id, m.city, BU.username from Municipalities M join BasicUsers BU on M.id = BU.id");
        ResultSet resultSet = getAll.executeQuery();

        // Add data in the array
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String city = resultSet.getString("city");
            String username = resultSet.getString("username");
            Municipality user = new Municipality(username, city);
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
