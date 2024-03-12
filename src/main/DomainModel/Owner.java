package main.DomainModel;

public class Owner extends BasicUser {
    public PrivatePlace privatePlace;
    private final String name;

    public Owner(String name, String username, String email, int id,
                 String city, PrivatePlace privatePlace) {
        super(id, email, username, city);
        this.privatePlace = privatePlace;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public PrivatePlace getPlace() {
        return privatePlace;
    }
}
