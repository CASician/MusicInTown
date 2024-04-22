package test.businessLogic;

import main.BusinessLogic.PlacesController;
import main.DomainModel.Owner;
import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PlacesControllerTest {

    @Test
    public void privatePlacesTest() throws Exception {
        PlacesController pc = new PlacesController();
        Owner o = new Owner("owner", "user");
        PrivatePlace p = new PrivatePlace("Firenze", "Hard Rock", "Piazza della Repubblica",
                1000, true, PlaceType.Cafe, o);
    }
    @BeforeAll
    static void setUpDb() throws SQLException, IOException {
        //Set up all the database
    }

    @BeforeEach
    public void initDb() throws SQLException {
        //Initialize the database
    }
}
