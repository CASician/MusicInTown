package test.domainModel;

import main.DAO.MunicipalityDAO;
import main.DomainModel.Municipality;
import main.DomainModel.Planner;
import main.DomainModel.PublicEvent;
import main.DomainModel.PublicPlace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

public class MunicipalityTest {
    Municipality m = MunicipalityDAO.getMunicipality("florence");

    Planner planner2 = new Planner("ecce", "homo");
    PublicPlace publicPlace = new PublicPlace("aerosol", "firenze", "piazza pubblica", 200, false);
    PublicEvent publicEvent = new PublicEvent("fantasia", true, LocalDate.of(2022, 2,2), planner2, publicPlace, "1", "firenze", "comico" );

    public MunicipalityTest() throws SQLException {
    }

    //propose event and delete event
    @Test
    public void proposeEventTest() {
        int num_events = m.getEventsToBeAccepted().size();
        m.propose_event(publicEvent);

        Assertions.assertEquals(num_events+1, m.getEventsToBeAccepted().size());
        m.delete_event(publicEvent);
    }

    @Test
    public void deleteEventTest() {
        m.propose_event(publicEvent);
        int num_events = m.getEventsToBeAccepted().size();
        m.delete_event(publicEvent);

        Assertions.assertEquals(num_events-1, m.getEventsToBeAccepted().size());
    }
}
