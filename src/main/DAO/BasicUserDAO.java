/*
You can use these functions to communicate with the DataBase. No need to know SQL language.
 */

package main.DAO;
import main.DomainModel.BasicUser;

import java.sql.*;
import java.util.ArrayList;

public class BasicUserDAO {
    public void add (BasicUser basicUser) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:"+"MusicInTown.db");
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");
        insertBasicUser.setString(1, basicUser.getEmail());
        insertBasicUser.setString(2, basicUser.getCity());
        insertBasicUser.setString(3, basicUser.getUsername());
        insertBasicUser.executeUpdate();
        insertBasicUser.close();
        connection.close();
    };

    //TODO: Implementare tutte le altre funzioni
    public void update (BasicUser basicUser){};
    public void delete(BasicUser basicUser){};
    //public BasicUser get(int i){};

    //public getAll{};
}
