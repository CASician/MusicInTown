package main.DomainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Musician extends BasicUser {
    List<PrivateEvent> privateEvents;
    List<PublicEvent> publicEvents;
    private final String name;
    private final String genre;
    private final int componentNumb;

    public Musician(String name, String username, String genre, String email, int numb,
                    String city) {
        super(username, email, city);
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
