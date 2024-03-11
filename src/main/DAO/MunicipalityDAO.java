package main.DAO;

import main.DomainModel.Municipality;
import java.sql.*;
public class MunicipalityDAO {

    public void add (Municipality municipality)  throws SQLException {
        // Connect to Database
        Connection conn = DriverManager.getConnection(DBconnection.jdbcUrl+"?user="+DBconnection.username+"&password="+DBconnection.password);
        PreparedStatement insertMunicipality = conn.prepareStatement("INSERT INTO Municipalities(id) VALUES(DEFAULT)");

        //No addition needed: the id is calculated automatically

        //Close connection
        insertMunicipality.executeUpdate();
        insertMunicipality.close();
        conn.close();

        // Show results
        System.out.println("New MUNICIPALITY added successfully!");
    }

    //TODO: implement other functions: update, delete, get(id), getAll

}
