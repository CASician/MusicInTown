package main.DomainModel;

/*
* Class that represent the concrete object of the Planner User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class Planner extends BasicUser {
    protected int id;
    protected String name;


    public Planner(int id, String name, String username) {
        super(id, username);
        this.name = name;
    }

    public String getName() { return name; }
}
