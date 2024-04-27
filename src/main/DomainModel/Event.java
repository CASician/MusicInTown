package main.DomainModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/*
* Abstract class that has all the common attributes and methods for all the event objects
*/
public abstract class Event {

    protected int id;
    protected String name;
    protected boolean open;
    protected LocalDate date;
    protected String city;
    protected String type;
    protected String duration;
    protected Boolean accepted;
    protected ArrayList<Musician> subscribers;
    public Event(String name, boolean open, LocalDate date, String city, String type, String duration) {
        this.name = name;
        this.open = open;
        this.date = date;
        this.city = city;
        this.type = type;
        this.duration = duration;
        this.accepted = Boolean.FALSE;
        subscribers = new ArrayList<>();
    }
    public void addSubscriber(Musician musician) {
        //Add the musician to the list of event subscription
        subscribers.add(musician);
    }

    public ArrayList<Musician> getSubscribers() { return subscribers; }

    public String getCity() {
        return city;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDuration() {
        return duration;
    }
    public String getType() {
        return type;
    }
    public void setId(int lastId) {
        this.id = lastId;
    }
    public boolean isOpen() { return open; }
    public void setOpen(boolean op) { open = op; }
    public void setAccepted(boolean bool){
        this.accepted = bool;
    }
    public Boolean isAccepted() {
        return accepted;
    }
}
