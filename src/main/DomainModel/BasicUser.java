package main.DomainModel;

public abstract class BasicUser {
    protected int id;
    protected String username;

    public BasicUser(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
