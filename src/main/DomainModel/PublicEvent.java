package main.DomainModel;

import java.util.Date;

public class PublicEvent extends Event {
    private final Planner planner;

    PublicPlace place;

    public PublicEvent(boolean open, Date date, Planner planner, PublicPlace place) {
        super(open, date);
        this.planner = planner;
        this.place = place;
    }

    public Planner getPlanner() {
        return planner;
    }

    public PublicPlace getPlace() {
        return place;
    }
}
