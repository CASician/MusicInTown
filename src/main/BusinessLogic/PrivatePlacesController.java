package main.BusinessLogic;

import main.DomainModel.PlaceType;
import main.DomainModel.PrivatePlace;

public class PrivatePlacesController {
    PrivatePlace privatePlace;
    public PrivatePlacesController(String owner) {
        this.privatePlace = new PrivatePlace(6, "Milano", "Alcatraz","Piazza Duomo",
                5000, true, owner, PlaceType.ConcertHall);
    }

    public PrivatePlace getPrivatePlace() {
        return privatePlace;
    }
}
