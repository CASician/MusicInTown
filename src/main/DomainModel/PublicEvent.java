package main.DomainModel;

import java.time.LocalDate;

public class PublicEvent extends Event {
    private final Planner planner;
    PublicPlace place;

    public PublicEvent(String name, boolean open, LocalDate date, Planner planner, PublicPlace place,
                       String duration, String city, String type) {
        super(name, open, date, city, type, duration);
        this.planner = planner;
        this.place = place;
    }


    public PublicPlace getPlace() {
        return place;
    }
}
