package main.DomainModel;

import java.time.LocalDate;

/*
* Class that represent the concrete object of the Public Events.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class PublicEvent extends Event {
    private final Planner planner;
    private final PublicPlace place;

    public PublicEvent(String name, boolean open, LocalDate date, Planner planner, PublicPlace place,
                       String duration, String city, String type) {
        super(name, open, date, city, type, duration);
        this.planner = planner;
        this.place = place;
    }
    public PublicPlace getPlace() {
        return place;
    }

    public Planner getPlanner() { return planner; }
}
