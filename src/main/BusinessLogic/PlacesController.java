package main.BusinessLogic;

import main.DAO.PlacesDAO;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

import java.util.HashMap;

public class PlacesController {
    protected PlacesDAO placesDAO;
    public PlacesController() {
        //Call the DAO to generate all the places
        placesDAO = new PlacesDAO();
    }

    public PlacesDAO getPlacesDAO() { return placesDAO; }

    /*
    public PublicPlace getPublicPlace(int id) {
        return placesDAO.getPublicPlaces().get(id);
    }
    public PrivatePlace getPrivatePlace(int id) {
        return placesDAO.getPrivatePlaces().get(id);
    }
    */

    public HashMap<Integer, PublicPlace> getPublicPlaces() {
        return placesDAO.getPublicPlaces();
    }
    public HashMap<Integer, PrivatePlace> getPrivatePlaces() {
        return placesDAO.getPrivatePlaces();
    }
}
