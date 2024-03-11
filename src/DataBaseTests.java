import main.DAO.BasicUserDAO;
import main.DomainModel.User;

import java.sql.SQLException;

public class DataBaseTests {
    public static void main(String[] string) throws SQLException {

        User miguel = new User(11, "miguelito", "miguelito@gmail.com", "lollone", "Firenze");
        BasicUserDAO.add(miguel);
    }
}
