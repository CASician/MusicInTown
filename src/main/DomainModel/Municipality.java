package main.DomainModel;

import java.util.List;

public class Municipality extends BasicUser {
    List<PublicPlace> publicPlaces;
    String city;

    public Municipality(String username, String city) {
        super(username);
        this.city = city;
    }

    public void setPublicPlaces(List<PublicPlace> publicPlaces) {
        this.publicPlaces = publicPlaces;
    }

    public String getCity() {
        return city;
    }
}
