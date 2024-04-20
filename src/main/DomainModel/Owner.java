package main.DomainModel;

import main.DAO.EventsToBeAcceptedDAO;
import main.DAO.PrivatePlaceDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/*
* Class that represent the concrete object of the Owner User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class Owner extends BasicUser {
    public PrivatePlace privatePlace;
    private final String name;

    ArrayList<PrivateEvent> eventsToBeAccepted = new ArrayList<>();

    public Owner(String username, String name, PrivatePlace privatePlace) throws SQLException {
        super(username);
        this.privatePlace = privatePlace;
        this.name = name;
    }

    public Owner(String username, String name) throws SQLException {
        super(username);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PrivatePlace getPlace() {
        return privatePlace;
    }

    public void setPrivatePlace(PrivatePlace privatePlace) {
        this.privatePlace = privatePlace;
    }
    public void propose_event(PrivateEvent event){eventsToBeAccepted.add(event);}

    public void remove_event(PrivateEvent event){eventsToBeAccepted.remove(event);}
    public ArrayList<PrivateEvent> getEventsToBeAccepted() {
        return eventsToBeAccepted;
    }

    public void setEventsToBeAccepted(ArrayList<PrivateEvent> eventsToBeAccepted) {
        this.eventsToBeAccepted = eventsToBeAccepted;
    }
}
