package main.DomainModel;

/*
* Class that represent the concrete object of the Owner User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
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
