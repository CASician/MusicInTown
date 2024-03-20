package main.DomainModel;

public class Planner extends BasicUser {
    protected String name;

    public Planner(int id, String name, String username) {

        super(id, username);
        this.name = name;
    }

    public String getName() { return name; }
}
