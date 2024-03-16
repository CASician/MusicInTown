package main.DomainModel;

public abstract class BasicUser {
    protected int id;
    protected String username;

    public BasicUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
