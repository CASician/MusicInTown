/*
You can use these functions to communicate with the DataBase. No need to know SQL language.
 */

package main.DAO;
import main.DomainModel.BasicUser;

import java.sql.*;
import java.util.ArrayList;
import main.DAO.DBconnection;
import org.postgresql.core.ResultCursor;

public class BasicUserDAO {
    public static void add (BasicUser basicUser) throws SQLException {
        // Connect to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, username) VALUES(DEFAULT, ?)");

        // Add the real values instead of "?"
        insertBasicUser.setString(1, basicUser.getUsername());

        // TODO: find the id value and give it to basicUser.id.
        // this way, in the subclasses they can simply use the getId.

        //Close connection
        insertBasicUser.executeUpdate();
        insertBasicUser.close();
        connection.close();

    }

    public static void delete(BasicUser basicUser) throws SQLException{
        // Connection to Database
        Connection connection = DriverManager.getConnection(DBconnection.jdbcUrl, DBconnection.username, DBconnection.password);

        // Use query to delete the instance
        PreparedStatement deleteBasicUser = connection.prepareStatement("delete from BasicUsers where username = ?");

        // Add the real value instead of "?"
        deleteBasicUser.setString(1, basicUser.getUsername());

        // Close connections
        deleteBasicUser.executeUpdate();
        deleteBasicUser.close();
        connection.close();

        // Show results
        System.out.println(basicUser.getUsername() + " has been deleted from DataBase");
    }
}
