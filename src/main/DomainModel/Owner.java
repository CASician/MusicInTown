package main.DomainModel;

public class Owner extends BasicUser {
    public PrivatePlace privatePlace;
    private final String name;

    public Owner(String name, String username, int id, PrivatePlace privatePlace) {
        super(id, username);
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
