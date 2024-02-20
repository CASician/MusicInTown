package main.DomainModel;

public class Municipality extends BasicUser {
    PublicPlace[] publicPlaces;

    public Municipality(int id, String email, String username, String city, PublicPlace[] publicPlaces) {
        super(id, email, username, city);
        this.publicPlaces = publicPlaces;
    }
}
