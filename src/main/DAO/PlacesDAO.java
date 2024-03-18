package main.DAO;

import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

import java.util.HashMap;

public class PlacesDAO {

    private final HashMap<Integer,PrivatePlace> privatePlaces;
    private final HashMap<Integer, PublicPlace> publicPlaces;

    public PlacesDAO() {
        //At every execution generate all the places objects
        privatePlaces = new HashMap<>();
        publicPlaces = new HashMap<>();
        PrivatePlace privatePlace1, privatePlace2, privatePlace3;
        PublicPlace publicPlace1, publicPlace2, publicPlace3;

        privatePlace1 = new PrivatePlace(0, "firenze", "viper", "via pistoiese",
                5000, true, PlaceType.ConcertHall);
        privatePlaces.put(privatePlace1.getId(), privatePlace1);

        privatePlace2 = new PrivatePlace(1, "milano", "alcatraz", "via rossi",
                10000, true, PlaceType.ConcertHall);
        privatePlaces.put(privatePlace2.getId(), privatePlace2);

        privatePlace3 = new PrivatePlace(2, "bologna", "covo", "piazza maggiore",
                5000, true, PlaceType.ConcertHall);
        privatePlaces.put(privatePlace3.getId(), privatePlace3);

        publicPlace1 = new PublicPlace(0, "firenze", "piazza santa croce",
                5000, false);
        publicPlaces.put(publicPlace1.getId(), publicPlace1);

        publicPlace2 = new PublicPlace(1, "milano", "piazza duomo",
                15000, false);
        publicPlaces.put(publicPlace2.getId(), publicPlace2);

        publicPlace3 = new PublicPlace(2, "milano", "teatro alla scala",
                5000, true);
        publicPlaces.put(publicPlace3.getId(), publicPlace3);
    }
    public PrivatePlace getPrivatePlace(Integer id) {
        return privatePlaces.getOrDefault(id, null);
    }
    public PublicPlace getPublicPlace(int id) {
        return publicPlaces.get(id);
    }

    public HashMap<Integer, PrivatePlace> getPrivatePlaces() {
        return privatePlaces;
    }
    public HashMap<Integer, PublicPlace> getPublicPlaces() {
        return publicPlaces;
    }


}
