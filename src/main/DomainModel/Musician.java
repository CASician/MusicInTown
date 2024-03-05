package main.DomainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Musician extends BasicUser {
    List<PrivateEvent> privateEvents;
    List<PublicEvent> publicEvents;
    public String name;
    public String genre;
    public int componentNumb;

    public Musician(String name, String genre, String username, String email, int id, int numb,
                    String city) {
        super(id, email, username, city);
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
        return privateEvents;
    }
    public List<PublicEvent> getPublicEvents() {
        return publicEvents;
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