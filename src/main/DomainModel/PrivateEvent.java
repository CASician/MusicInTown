package main.DomainModel;

import java.time.LocalDate;

public class PrivateEvent extends Event {
    PrivatePlace place;
    Planner planner;
    Owner ownerPlanner;

    public PrivateEvent(String name, boolean open, LocalDate date, Planner planner, PrivatePlace place,
                        String duration, String city, String type) {
        super(name, open, date, city, type, duration);
        this.ownerPlanner = null;
        this.planner = planner;
        this.place = place;
    }

    public PrivateEvent(String name, boolean open, LocalDate date, Owner owner, PrivatePlace place,
                        String duration, String city, String type) {
        super(name, open, date, duration, city, type);
        this.planner = null;
        this.place = place;
        this.ownerPlanner = owner;
    }

    public Planner getPlanner() {
        return planner;
    }

    public Owner getOwnerPlanner() {
        return ownerPlanner;
    }

    public PrivatePlace getPlace() {
        return place;
    }
}
