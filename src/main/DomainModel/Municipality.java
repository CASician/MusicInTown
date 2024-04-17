package main.DomainModel;

import main.DAO.EventsToBeAcceptedDAO;

import java.sql.SQLException;
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

    public Municipality(String username, String city) throws SQLException {
        super(username);
        this.city = city;
        //eventsToBeAccepted = EventsToBeAcceptedDAO.getAll_municipality(this);
        // TODO add this to DAO instead. lol. this was for owner
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

    public void setEventsToBeAccepted(ArrayList<PublicEvent> eventsToBeAccepted) {
        this.eventsToBeAccepted = eventsToBeAccepted;
    }

    public void delete_event(PublicEvent event) { eventsToBeAccepted.remove(event); }
}
