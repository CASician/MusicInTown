package test.DAO;

import main.DAO.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class getAllsDAO {
    // ---------------------------- GET ALL USERS ----------------------------

    @Test
    public void getAllUsersTest() throws Exception{
        Assertions.assertEquals(3, UserDAO.getAll().size());
    }
    @Test
    public void getAllMunicipalitiesTest() throws Exception{
        Assertions.assertEquals(1, MunicipalityDAO.getAll().size());
    }
    @Test
    public void getAllMusiciansTest() throws Exception{
        Assertions.assertEquals(5, MusicianDAO.getAll().size());
    }
    @Test
    public void getAllOwnersTest() throws Exception{
        Assertions.assertEquals(5, OwnerDAO.getAll().size());
    }
    @Test
    public void getAllPlannersTest() throws Exception{
        Assertions.assertEquals(5, PlannerDAO.getAll().size());
    }

    // ---------------------------- GET ALL PLACES ----------------------------
    @Test
    public void getAllPrivatePlacesTest() throws Exception{
        int num = PrivatePlaceDAO.getAll().size();
        Assertions.assertEquals(5, num);
    }

    @Test
    public void getAllPublicPlacesTest() throws Exception{
        Assertions.assertEquals(5, PublicPlaceDAO.getAll().size());
    }

    // ---------------------------- GET ALL EVENTS ----------------------------

    @Test
    public void getAllPrivateEventsTest() throws Exception{
        Assertions.assertEquals(8, PrivateEventDAO.getAll().size());
    }

    @Test
    public void getAllPublicEventsTest() throws Exception{
        Assertions.assertEquals(5, PublicEventDAO.getAll().size());
    }
}
