package main.DomainModel;

/*
* Class that represent the concrete object of the Basic User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class User extends BasicUser {

    private final String name;

    public User(int id, String name, String username) {
        super(id, username);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
