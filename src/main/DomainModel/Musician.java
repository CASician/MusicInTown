package main.DomainModel;

import main.DAO.SubscriptionsDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/*
* Class that represent the concrete object of the Musician User.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class Musician extends BasicUser {
    private ArrayList<PrivateEvent> subscriptions_privateEvents;
    private ArrayList<PublicEvent> subscriptions_publicEvents;
    private final String name;
    private final String genre;
    private final int componentNumb;

    public Musician(String username, String name, String genre, int numb) {
        super(username);
        subscriptions_publicEvents = new ArrayList<>();
        subscriptions_privateEvents = new ArrayList<>();
        this.name = name;
        this.genre = genre;
        this.componentNumb = numb;
    }
    public void addSubscription(Event event){
        if (event instanceof PublicEvent){
            // downcast to Public Event
            subscriptions_publicEvents.add((PublicEvent) event);
        } else {
            // downcast to Private Event
            subscriptions_privateEvents.add((PrivateEvent) event);
        }
    }
    public ArrayList<PrivateEvent> getPrivateEvents() throws SQLException {
        //Returns the list of private events
        return subscriptions_privateEvents = SubscriptionsDAO.getAllPrivate_musician(this);
    }
    public ArrayList<PublicEvent> getPublicEvents() throws SQLException {
        //Returns the list of public events
        return subscriptions_publicEvents = SubscriptionsDAO.getAllPublic_musician(this);
    }

    public ArrayList<PublicEvent> test_getSubscriptions_publicEvents() {
        return subscriptions_publicEvents;
    }

    public ArrayList<PrivateEvent> test_getSubscriptions_privateEvents() {
        return subscriptions_privateEvents;
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
