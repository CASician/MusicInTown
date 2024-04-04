package main.DomainModel;

import main.DAO.PrivatePlaceDAO;

/*
* Class that represent the concrete object of the Owner User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class Owner extends BasicUser {
    public PrivatePlace privatePlace;
    private final String name;

    public Owner(String username, String name, PrivatePlace privatePlace) {
        super(username);
        this.privatePlace = privatePlace;
        this.name = name;
    }

    public Owner(String username, String name){
        super(username);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public PrivatePlace getPlace() {
        return privatePlace;
    }

    public void setPrivatePlace(PrivatePlace privatePlace) {
        this.privatePlace = privatePlace;
    }
}
