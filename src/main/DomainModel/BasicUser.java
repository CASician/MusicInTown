package main.DomainModel;

public abstract class BasicUser {
    protected int id;
    protected String email;
    protected String username;

    public BasicUser(int id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
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

}
