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

        // Insert the BasicUser
        PreparedStatement insertBasicUser = connection.prepareStatement("INSERT INTO BasicUsers(id, username) VALUES(DEFAULT, ?)");
        // Add the real values instead of "?"
        insertBasicUser.setString(1, basicUser.getUsername());
        insertBasicUser.executeUpdate();

        // Use a query to find what ID has been automatically assigned.
        PreparedStatement findId = connection.prepareStatement("SELECT id FROM BasicUsers WHERE username = ?");
        findId.setString(1, basicUser.getUsername());

        // Use the result to assign the ID
        ResultSet resultSet = findId.executeQuery();
        resultSet.next();                           // Idk what it does, but it's needed.
        basicUser.setId(resultSet.getInt("id"));

        //Close connection
        findId.close();
        resultSet.close();
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
