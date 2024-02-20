package main.DomainModel;

import java.util.Date;

public class Owner extends BasicUser {
    public Place place;
    private String name;

    public Owner(String name, String username, String email, int id,
                 String city, Place place) {
        super(id, email, username, city);
        this.place = place;
        this.name = name;

    }
}
