package main.DomainModel;

import java.util.Date;

public class PrivateEvent extends Event {
    PrivatePlace place;

    Planner planner;

    Owner ownerPlanner;

    public PrivateEvent(boolean open, Date date, Planner planner, PrivatePlace place) {
        super(open, date);
        this.ownerPlanner = null;
        this.planner = planner;
        this.place = place;
    }

    public PrivateEvent(boolean open, Date date, Owner owner, PrivatePlace place) {
        super(open, date);
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
