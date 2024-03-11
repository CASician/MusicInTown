package main.DomainModel;

public class User extends BasicUser {

    public String name;

    public User(int id, String name, String email, String username, String city) {

        super(id, email, username, city);
        this.name = name;
    }

}
