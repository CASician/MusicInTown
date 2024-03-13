package main.DomainModel;

public abstract class BasicUser {
    protected String email;
    protected String city;
    protected String username;

    public BasicUser(String username, String email, String city) {
        this.email = email;
        this.username = username;
        this.city = city;
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
