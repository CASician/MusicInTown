package main.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static String jdbcUrl = "jdbc:postgresql://localhost:5432/MusicInTown";
    public static String username = "postgres";
    public static String password = "MusicInTown";
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Driver JDBC di PostgreSQL non trovato.");
            e.printStackTrace();
            return;
        }

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connessione al database riuscita!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

