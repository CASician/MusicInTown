package main.DomainModel;

import java.util.ArrayList;
import java.util.List;

/*
* Class that represent the concrete object of the Municipality User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/

public class Municipality extends BasicUser {
    List<PublicPlace> publicPlaces;
    String city;
    ArrayList<PublicEvent> eventsToBeAccepted;

    public Municipality(String username, String city) {
        super(username);
        this.city = city;
        eventsToBeAccepted = new ArrayList<PublicEvent>();
    }

    public void setPublicPlaces(List<PublicPlace> publicPlaces) {
        this.publicPlaces = publicPlaces;
    }

    public String getCity() {
        return city;
    }

    public void propose_event(PublicEvent event){
        eventsToBeAccepted.add(event);
    }

    public ArrayList<PublicEvent> getEventsToBeAccepted() {
        return eventsToBeAccepted;
    }
}
