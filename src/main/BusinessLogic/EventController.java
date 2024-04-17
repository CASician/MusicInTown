package main.BusinessLogic;

import main.DAO.EventDAO;
import main.DAO.PrivateEventDAO;
import main.DAO.PublicEventDAO;
import main.DomainModel.*;
import main.Interface.BasicUserInterface;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
* Class that implements all the methods to manage the events.
* It communicates with the EventDAO and generates/modify public and private events
*/
public class EventController implements Subject {
    PublicEvent publicEvent;
    PrivateEvent privateEvent;
    //private final EventDAO eventsDAO;
    ArrayList<PublicEvent> publicEvents;
    ArrayList<PrivateEvent> privateEventsList;
    private final BasicUserInterface basicUserInterface;
    ArrayList<Observer> observers = new ArrayList<>();

    public EventController(PlacesController placesController) {
        //eventsDAO = new EventDAO();
        basicUserInterface = new BasicUserInterface();
    }

    public PublicEvent subscribePublicEvent(String musicianName, int musicianId, int eventId) throws SQLException {
        /*
        * Method used from the musician to subscribe itself to a specific public event between musician ID and event ID.
        * It checks if the event exists and if the musician is already subscribed.
        */
        boolean found = false;
        boolean alreadySubscribed = false;
        publicEvent = null;
        publicEvents = PublicEventDAO.getAll();
        for (PublicEvent event : publicEvents) {
            if (event.getId() == eventId) {
                found = true;
                if(!event.getSubscriptions().containsKey(musicianId)) {
                    publicEvent = event;
                }
                else { alreadySubscribed = true; }
                break;
            }
        }
        if(!found) {
            basicUserInterface.eventNotFound();
        }
        else {
            if(!alreadySubscribed) {
                publicEvent.addSubscription(musicianName, musicianId);
            }
            else {
                basicUserInterface.alreadySubscribed();
            }
        }
        return publicEvent;
    }

    public PrivateEvent subscribePrivateEvent(String musicianName, int musicianId, int eventId) throws SQLException {
        /*
         * Method used from the musician to subscribe itself to a specific private event between musician ID and event ID.
         * It checks if the event exists and if the musician is already subscribed.
         */
        boolean found = false;
        boolean alreadySubscribed = false;
        privateEvent = null;
        privateEventsList = PrivateEventDAO.getAll();
        // Double for cycle because private Events have two different signatures.
        for (PrivateEvent event : privateEventsList) {
            if (event.getId() == eventId) {
                found = true;
                if (!event.getSubscriptions().containsKey(musicianId)) {
                    privateEvent = event;
                } else {
                    alreadySubscribed = true;
                }
                break;
            }
        }
        /*
        for (PrivateEvent event : privateEventsList.getByPlanner()) {
            if (event.getId() == eventId) {
                found = true;
                if (!event.getSubscriptions().containsKey(musicianId)) {
                    privateEvent = event;
                } else {
                    alreadySubscribed = true;
                }
                break;
            }
        }

         */
        if(!found) {
            basicUserInterface.eventNotFound();
        }
        else {
            if(!alreadySubscribed) {
                privateEvent.addSubscription(musicianName, musicianId);
            }
            else {
                basicUserInterface.alreadySubscribed();
            }
        }
        return privateEvent;
    }

    public ArrayList<PublicEvent> getPublicEvents() throws SQLException {
        //Returns a list of all the public events
        if(publicEvents == null) {
            publicEvents = PublicEventDAO.getAll();
        }
        return publicEvents;
    }

    public ArrayList<PrivateEvent> getPrivateEvents() throws SQLException {
        //Returns a list of all the private events
        if(privateEventsList == null) {
            privateEventsList = PrivateEventDAO.getAll();
        }
        return privateEventsList;
    }

    public ArrayList<PrivateEvent> getPrivateEventsFiltered(LocalDate date) throws SQLException {
        //Returns a list of all the private events filtered until a specific date
        ArrayList<PrivateEvent> filteredEvents = new ArrayList<>();
        if(privateEventsList == null) {
            privateEventsList = PrivateEventDAO.getAll();
        }
        for (PrivateEvent event : privateEventsList) {
            if (!date.isBefore(event.getDate())) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
    public ArrayList<PublicEvent> getPublicEventsFiltered(LocalDate date) throws SQLException {
        //Returns a list of all the public events filtered until a specific date
        ArrayList<PublicEvent> filteredEvents = new ArrayList<>();
        if(publicEvents == null) {
            publicEvents = PublicEventDAO.getAll();
        }
        for (PublicEvent event : publicEvents) {
            if (!date.isBefore(event.getDate())) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    /*
    public void getEventSubscriptions(int id) {
        Search in all the events the one with the right ID

    }
    */

    @Override
    public void notifyEventObservers(Event event) throws SQLException {
        for(Observer o: observers){
            o.update(event);
        }
    }

    @Override
    public void notifyPlaceObservers(int placeId) {

    }

    @Override
    public void attach(Observer o){observers.add(o);}

    @Override
    public void detach(Observer o){observers.remove(o);}

    public PrivateEvent createPrivateEvent(String name, Boolean open, LocalDate date, Owner owner,
                            PrivatePlace privatePlace, String duration, String city, String type) throws SQLException {
        //Creates a new private event and it saves it inside the database using the EventDAO
        PrivateEvent event = new PrivateEvent(
                name, open, date, owner, privatePlace, duration, city, type);
        PrivateEventDAO.add(event);
        // Add the event to the owner's list of proposed events by notifying the addition.
        notifyEventObservers(event);
        return event;
    }

    public PrivateEvent createPrivateEvent(String name, Boolean open, LocalDate date, Planner planner,
                                           PrivatePlace privatePlace, String duration, String city, String type) throws SQLException {
        //Same as before but instead of owner this one is organized by the planner
        PrivateEvent event = new PrivateEvent(
                name, open, date, planner, privatePlace, duration, city, type);
        PrivateEventDAO.add(event);
        // Add the event to the owner's list of proposed events by notifying the addition
        notifyEventObservers(event);
        return event;
    }

    public PublicEvent createPublicEvent(String eventName, Boolean open, LocalDate date, Planner planner,
                                         PublicPlace publicPlace, String duration, String city, String eventType) throws SQLException {
        //Creates a new public event and it saves it inside the database using the EventDAO
        PublicEvent event = new PublicEvent(
                eventName, open, date, planner, publicPlace, duration, city, eventType);
        PublicEventDAO.add(event);
        notifyEventObservers(event);
        return event;
    }
}
