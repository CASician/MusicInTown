package main.DomainModel;

public class Municipality extends BasicUser {
    PublicPlace[] publicPlaces;

    public Municipality(String username, String email, String city, PublicPlace[] publicPlaces) {
        super(username, email, city);
        this.publicPlaces = publicPlaces;
    }
}
