package test.domainModel;

import main.DomainModel.Owner;
import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OwnerPlaceTest {

    @Test
    public void owner_test() throws Exception {
        Owner o = new Owner("user", "owner");
        Assertions.assertEquals("owner", o.getName());
        Assertions.assertEquals("user", o.getUsername());
    }
    @Test
    public void ownerPlace_test() throws Exception {
        Owner o = new Owner("owner", "user");
        PrivatePlace p = new PrivatePlace("Firenze", "Hard Rock", "Piazza della Repubblica",
                1000, true, PlaceType.Cafe, o);
        Assertions.assertEquals( "Hard Rock", p.getName());
        Assertions.assertEquals( "Firenze", p.getCity());
        Assertions.assertEquals( 1000, p.getCapacity());
        Assertions.assertEquals( PlaceType.Cafe, p.getType());
        Assertions.assertEquals( "owner", p.getOwnerName());
    }

    @Test
    public void publicPlace_test() throws  Exception {
        PublicPlace pp = new PublicPlace("Santa Croce", "Firenze", "Santa Croce",
                10000, false);
        Assertions.assertEquals( "Santa Croce", pp.getName());
        Assertions.assertEquals(10000, pp.getCapacity());
        pp.setId(5);
        Assertions.assertEquals( 5, pp.getId());
    }
}
