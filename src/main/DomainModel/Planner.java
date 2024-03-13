package main.DomainModel;

public class Planner extends BasicUser {

    int[] createdEvents;
    public Planner(String username, String email, String city) {
        super(username, email, city);
    }
}
