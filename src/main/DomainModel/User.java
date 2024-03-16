package main.DomainModel;

public class User extends BasicUser {

    private final String name;

    public User(int id, String name, String email, String username) {

        super(id, email, username);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
