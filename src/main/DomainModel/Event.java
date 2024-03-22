package main.DomainModel;

import java.time.LocalDate;
import java.util.HashMap;

/*
* Abstract class that has all the common attributes and methods for all the event objects
*/
public abstract class Event {

    protected int id;
    protected boolean open;
    protected LocalDate date;
    protected String city;
    protected String type;
    protected String duration;
    protected String name;
    protected HashMap<Integer, String> subscriptions;

    public Event(String name, boolean open, LocalDate date, String city, String type, String duration) {
        this.name = name;
        this.open = open;
        this.date = date;
        this.city = city;
        this.type = type;
        this.duration = duration;
        subscriptions = new HashMap<>();
    }

    public void addSubscription(String musicianName, int musicianId) {
        //Add the musician ID and band name to the list of event subscription
        subscriptions.put(musicianId, musicianName);
    }

    public HashMap<Integer, String> getSubscriptions() { return subscriptions; }

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

}
