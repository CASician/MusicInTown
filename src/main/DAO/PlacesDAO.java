package main.DAO;

import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

public class PlacesDAO {

    PrivatePlace[] privatePlaces;
    PublicPlace[] publicPlaces;

    public PlacesDAO() {
        privatePlaces = new PrivatePlace[3];
        publicPlaces = new PublicPlace[3];

        privatePlaces[0] = new PrivatePlace(1, "firenze", "viper", "via pistoiese",
                5000, true, 1, PlaceType.ConcertHall);
        privatePlaces[1] = new PrivatePlace(2, "milano", "alcatraz", "via rossi",
                10000, true, 1, PlaceType.ConcertHall);
        privatePlaces[2] = new PrivatePlace(3, "bologna", "covo", "piazza maggiore",
                5000, true, 1, PlaceType.ConcertHall);

        publicPlaces[0] = new PublicPlace(1, "firenze", "piazza santa croce",
                5000, false);
        publicPlaces[1] = new PublicPlace(2, "milano", "piazza duomo",
                15000, false);
        publicPlaces[2] = new PublicPlace(3, "milano", "teatro alla scala",
                5000, true);
    }

}
