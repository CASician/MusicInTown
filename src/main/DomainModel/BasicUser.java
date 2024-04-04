package main.DomainModel;

/*
* Abstract class that has all the common attributes and methods for all the user objects
*/
public abstract class BasicUser {
    protected int id;
    protected String username;


    public BasicUser(String username) {
        this.username = username;
    }

    public int getId() {
        //Getter of the ID
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername() {
        //Getter of the username
        return username;
    }

}
