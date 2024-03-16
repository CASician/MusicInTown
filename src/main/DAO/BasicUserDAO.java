/*
You can use these functions to communicate with the DataBase. No need to know SQL language.
 */

package main.DAO;
import main.DomainModel.BasicUser;

import java.sql.*;

public class BasicUserDAO {
    public static void add (BasicUser basicUser) throws SQLException {
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, email, city, username) VALUES(DEFAULT, ?, ?, ?)");

        // Add the real values instead of "?"
        insertBasicUser.setString(1, basicUser.getEmail());
        insertBasicUser.setString(3, basicUser.getUsername());

        //Close connection
        insertBasicUser.executeUpdate();
        insertBasicUser.close();
        connection.close();

        //Show result
        System.out.println("New BASIC USER added successfully!");
    }

    //TODO: create ADD function on all basicUsers: municipality, musician, owner, planner, user
    //TODO: create ADD function on all places and events: places, privatePlaces, publicPlaces, events, publicEvents, privateEvents.

    //TODO: Implementare tutte le altre funzioni
    public void update (BasicUser basicUser){}
    public void delete(BasicUser basicUser){}
    //public BasicUser get(int i){}

    //public getAll{};
}
