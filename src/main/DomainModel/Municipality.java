package main.DomainModel;

public class Municipality extends BasicUser {
    PublicPlace[] publicPlaces;

    public Municipality(String username, String email, String city, PublicPlace[] publicPlaces) {
        super(email, username, city);
        this.publicPlaces = publicPlaces;
    }
}
