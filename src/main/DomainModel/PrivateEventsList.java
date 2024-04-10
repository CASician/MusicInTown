package main.DomainModel;

import java.util.ArrayList;

public class EventsList {
    ArrayList<PublicEvent> publicEvents;

    public ArrayList<PublicEvent> getPublicEvents() {
        return publicEvents;
    }

    public void setPublicEvents(ArrayList<PublicEvent> publicEvents) {
        this.publicEvents = publicEvents;
    }

    ArrayList<PrivateEvent> privateEvents;


    public ArrayList<PrivateEvent> getPrivateEvents() {
        return privateEvents;
    }

    public void setPrivateEvents(ArrayList<PrivateEvent> privateEvents) {
        this.privateEvents = privateEvents;
    }


}
