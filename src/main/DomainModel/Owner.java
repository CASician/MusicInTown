package main.DomainModel;

public class Owner extends BasicUser {
    public PrivatePlace privatePlace;
    private final String name;

    public Owner(String name, String username, String email,
                 String city, PrivatePlace privatePlace) {
        super(username, email, city);
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
