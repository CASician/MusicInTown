package main.DomainModel;

public abstract class BasicUser {
    protected int id;
    protected String email;

    protected String city;
    public String username;

    public BasicUser(int id, String email, String username, String city) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getCity() {
        return city;
    }
}
