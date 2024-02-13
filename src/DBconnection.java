import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/MusicInTown";
        String username = "postgres";
        String password = "MusicInTown";

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

