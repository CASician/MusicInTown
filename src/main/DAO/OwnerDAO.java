package main.DAO;

import main.BusinessLogic.PlacesController;
import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;

public class OwnerDAO {
    Owner owner;
    PrivatePlace privatePlace;
    PlacesDAO placesDAO;

    public OwnerDAO(String owner, PlacesController placesController) {
        //Access the database to retrieve the info of the owner and generate a new instance.
        //The instance must contain the place that he owns retrieved from the database
        placesDAO = placesController.getPlacesDAO();
        this.privatePlace = placesDAO.getPrivatePlace(1);
        this.owner = new Owner("owner", owner, "owner@gmail.com", 5,"Firenze", this.privatePlace);
    }

    public Owner getOwner() {
        return owner;
    }
}
