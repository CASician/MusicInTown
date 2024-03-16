package main.DomainModel;

import java.util.List;

public class Municipality extends BasicUser {
    List<PublicPlace> publicPlaces;
    String city;

    public Municipality(int id, String email, String username, String city, List<PublicPlace> publicPlaces) {
        super(id, email, username);
        this.city = city;
        this.publicPlaces = publicPlaces;
    }

    public String getCity() {
        return city;
    }
}
