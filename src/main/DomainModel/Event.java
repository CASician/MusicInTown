package main.DomainModel;

import java.util.Date;

public abstract class Event {
    protected int id;
    protected boolean open;
    protected Date date;

    public Event(boolean open, Date date) {
        this.open = open;
        this.date = date;
    }
}
