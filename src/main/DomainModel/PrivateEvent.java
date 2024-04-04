package main.DomainModel;

import java.time.LocalDate;

/*
* Class that represent the concrete object of the Private Events.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class PrivateEvent extends Event {
    private final PrivatePlace place;
    private final Planner planner;
    private final Owner ownerPlanner;

    public PrivateEvent(String name, boolean open, LocalDate date, Planner planner, PrivatePlace place,
                        String duration, String city, String type) {
        super(name, open, date, city, type, duration);
        this.ownerPlanner = null;
        this.planner = planner;
        this.place = place;
    }

    public PrivateEvent(String name, boolean open, LocalDate date, Owner owner, PrivatePlace place,
                        String duration, String city, String type) {
        super(name, open, date, city, type, duration);
        this.planner = null;
        this.place = place;
        this.ownerPlanner = owner;
    }

    public PrivatePlace getPlace() {
        return place;
    }
    public Planner getPlanner(){
        return planner;
    }
    public Owner getOwnerPlanner(){
        return ownerPlanner;
    }
}
