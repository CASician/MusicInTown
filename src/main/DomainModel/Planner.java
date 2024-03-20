package main.DomainModel;

public class Planner extends BasicUser {
    protected String name;

    public Planner(String name, String username) {

        super(username);
        this.name = name;
    }

    public String getName() { return name; }
}
