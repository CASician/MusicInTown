package main.DomainModel;

public class Planner extends BasicUser {

    int[] createdEvents;
    public Planner(int id, String email, String username, String city) {
        super(id, email, username, city);
    }
}
