package main.DomainModel;

import java.util.ArrayList;
import java.util.List;

public class Musician extends BasicUser {
    List<PrivateEvent> privateEvents;
    List<PublicEvent> publicEvents;
    private final String name;
    private final String genre;
    private final int componentNumb;

    public Musician(String name, String genre, String username, int id, int numb) {
        super(id, username);
        publicEvents = new ArrayList<>();
        privateEvents = new ArrayList<>();
        this.name = name;
        this.genre = genre;
        this.componentNumb = numb;
    }
    public void addPublicSubscription(PublicEvent event) {
        publicEvents.add(event);
    }
    public void addPrivateSubscription(PrivateEvent event) {
        privateEvents.add(event);
    }
    public List<PrivateEvent> getPrivateEvents() {
        if(privateEvents.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return privateEvents;
        }
    }
    public List<PublicEvent> getPublicEvents() {
        if(publicEvents.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return publicEvents;
        }
    }
    public int getComponentNumb() {
        return componentNumb;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

}
