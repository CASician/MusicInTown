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
    public PlacesController() {
        //Call the DAO to generate all the places
    }

    public ArrayList<PublicPlace> getPublicPlaces() throws SQLException {
        //return an array with the public place object
        return PublicPlaceDAO.getAll();
    }
    public ArrayList<PrivatePlace> getPrivatePlaces() throws SQLException {
        //return an array with the private place object
        return PrivatePlaceDAO.getAll();
    }

    public PrivatePlace getPrivatePlace(String name) throws SQLException {
        //Returns a specific private place associated with the id taken as a parameter
        return PrivatePlaceDAO.getPrivatePlace(name);
    }

    public PublicPlace getPublicPlace(String name) throws SQLException {
        //Returns a specific public place associated with the id taken as a parameter
        return PublicPlaceDAO.getPublicPlace(name);
    }
}
