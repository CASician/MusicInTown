package main.DomainModel;

public class User extends BasicUser {

    private final String name;

    public User(String name, String username) {

        super(username);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
