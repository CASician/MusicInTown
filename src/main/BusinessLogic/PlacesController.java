package main.BusinessLogic;

import main.DAO.PlacesDAO;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

import java.util.HashMap;
import java.util.List;

/*
* Class that communicates with the DAO to obtain info about the places
*/
public class PlacesController {
    private final PlacesDAO placesDAO;
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
        //return an hashmap with the ID and the public place object
        return placesDAO.getPublicPlaces();
    }
    public HashMap<Integer, PrivatePlace> getPrivatePlaces() {
        //return an hashmap with the ID and the private place object
        return placesDAO.getPrivatePlaces();
    }

    public PrivatePlace getPrivatePlace(int id) {
        //Returns a specific private place associated with the id taken as a parameter
        return placesDAO.getPrivatePlace(id);
    }

    public PublicPlace getPublicPlace(int id) {
        //Returns a specific public place associated with the id taken as a parameter
        return placesDAO.getPublicPlace(id);
    }

    public List<PrivatePlace> getPrivatePlacesList() {
        //Returns a list with all the private place objects
        return placesDAO.getPrivatePlacesList();
    }
    public List<PublicPlace> getPublicPlacesList() {
        //Returns a list with all the public place objects
        return placesDAO.getPublicPlacesList();
    }
}
