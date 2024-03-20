package main.DomainModel;

public abstract class BasicUser {
    protected int id;
    protected String username;

    public BasicUser(String username) {
        // ID is not assigned in the constructor, because you need database connection for the proper id.
        // See DAO or Controllers
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

}
