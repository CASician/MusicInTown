package main.DAO;

import main.DomainModel.Municipality;
import java.sql.*;
public class MunicipalityDAO {

    public void add (Municipality municipality)  throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql:"+"MusicInTown.db");
        PreparedStatement insertMunicipality = conn.prepareStatement("INSERT INTO Municipalities(id) VALUES(DEFAULT)");
        insertMunicipality.executeUpdate();
        insertMunicipality.close();
        conn.close();
    }

    //TODO: implement other functions: update, delete, get(id), getAll

}
