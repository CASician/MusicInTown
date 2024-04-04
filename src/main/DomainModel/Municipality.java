package main.DomainModel;

import java.util.List;

/*
* Class that represent the concrete object of the Municipality User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/

public class Municipality extends BasicUser {
    List<PublicPlace> publicPlaces;
    String city;

    public Municipality(int id, String username, String city) {
        super(id, username);
        this.city = city;
    }

    public void setPublicPlaces(List<PublicPlace> publicPlaces) {
        this.publicPlaces = publicPlaces;
    }

    public String getCity() {
        return city;
    }
}
