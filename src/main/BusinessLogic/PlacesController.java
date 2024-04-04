package main.BusinessLogic;

import main.DAO.PlaceDAO;
import main.DAO.PrivatePlaceDAO;
import main.DAO.PublicPlaceDAO;
import main.DomainModel.PrivatePlace;
import main.DomainModel.PublicPlace;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* Class that communicates with the DAO to obtain info about the places
*/
public class PlacesController {
    private final PublicPlaceDAO publicPlaceDAO;
    private final PrivatePlaceDAO privatePlaceDAO;
    public PlacesController() {
        //Call the DAO to generate all the places
        publicPlaceDAO = new PublicPlaceDAO();
        privatePlaceDAO = new PrivatePlaceDAO();
    }

    // Todo credo? Ci serve questa funzione unita?
    //public PlaceDAO getPlacesDAO() { return placesDAO; }

    public ArrayList<PublicPlace> getPublicPlaces() throws SQLException {
        //return an array with the public place object
        return publicPlaceDAO.getAll();
    }
    public ArrayList<PrivatePlace> getPrivatePlaces() throws SQLException {
        //return an array with the private place object
        return privatePlaceDAO.getAll();
    }

    public PrivatePlace getPrivatePlace(String name) throws SQLException {
        //Returns a specific private place associated with the id taken as a parameter
        return PrivatePlaceDAO.getPrivatePlace(name);
    }

    public PublicPlace getPublicPlace(String name) throws SQLException {
        //Returns a specific public place associated with the id taken as a parameter
        return publicPlaceDAO.getPublicPlace(name);
    }
}
