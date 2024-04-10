package main.DomainModel;

import java.util.ArrayList;
import java.util.List;

/*
* Class that represent the concrete object of the Musician User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class Musician extends BasicUser {
    private final ArrayList<PrivateEvent> privateEvents;
    private final ArrayList<PublicEvent> publicEvents;
    private final String name;
    private final String genre;
    private final int componentNumb;

    public Musician(String username, String name, String genre, int numb) {
        super(username);
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
    public ArrayList<PrivateEvent> getPrivateEvents() {
        //Returns the list of private events
        if(privateEvents.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return privateEvents;
        }
    }
    public ArrayList<PublicEvent> getPublicEvents() {
        //Returns the list of public events
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
