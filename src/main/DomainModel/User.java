package main.DomainModel;

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
