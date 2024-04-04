package main.BusinessLogic;

import main.DAO.EventsDAO;
import main.DomainModel.*;
import main.Interface.BasicUserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* Class that implements all the methods to manage the events.
* It communicates with the EventDAO and generates/modify public and private events
*/
public class EventController implements Subject {
    PublicEvent publicEvent;
    PrivateEvent privateEvent;
    private final EventsDAO eventsDAO;
    List<PublicEvent> publicEvents;
    List<PrivateEvent> privateEvents;
    private final BasicUserInterface basicUserInterface;

    public EventController(PlacesController placesController) {
        eventsDAO = new EventsDAO(placesController);
        basicUserInterface = new BasicUserInterface();
    }

    public PublicEvent subscribePublicEvent(String musicianName, int musicianId, int eventId) {
        /*
        * Method used from the musician to subscribe itself to a specific public event between musician ID and event ID.
        * It checks if the event exists and if the musician is already subscribed.
        */
        boolean found = false;
        boolean alreadySubscribed = false;
        publicEvent = null;
        publicEvents = eventsDAO.getPublicEvents();
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

    public PrivateEvent subscribePrivateEvent(String musicianName, int musicianId, int eventId) {
        /*
         * Method used from the musician to subscribe itself to a specific private event between musician ID and event ID.
         * It checks if the event exists and if the musician is already subscribed.
         */
        boolean found = false;
        boolean alreadySubscribed = false;
        privateEvent = null;
        privateEvents = eventsDAO.getPrivateEvents();
        for (PrivateEvent event : privateEvents) {
            if (event.getId() == eventId) {
                found = true;
                if(!event.getSubscriptions().containsKey(musicianId)) {
                    privateEvent = event;
                } else { alreadySubscribed = true; }
                break;
            }
        }
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

    public List<PublicEvent> getPublicEvents() {
        //Returns a list of all the public events
        if(publicEvents == null) {
            publicEvents = eventsDAO.getPublicEvents();
        }
        return publicEvents;
    }

    public List<PrivateEvent> getPrivateEvents() {
        //Returns a list of all the private events
        if(privateEvents == null) {
            privateEvents = eventsDAO.getPrivateEvents();
        }
        return privateEvents;
    }

    public List<PrivateEvent> getPrivateEventsFiltered(LocalDate date) {
        //Returns a list of all the private events filtered until a specific date
        List<PrivateEvent> filteredEvents = new ArrayList<>();
        if(privateEvents == null) {
            privateEvents = eventsDAO.getPrivateEvents();
        }
        for (PrivateEvent event : privateEvents) {
            if (!date.isBefore(event.getDate())) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
    public List<PublicEvent> getPublicEventsFiltered(LocalDate date) {
        //Returns a list of all the public events filtered until a specific date
        List<PublicEvent> filteredEvents = new ArrayList<>();
        if(publicEvents == null) {
            publicEvents = eventsDAO.getPublicEvents();
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
    public void notifyEventObservers(int eventId) {

    }

    @Override
    public void notifyPlaceObservers(int placeId) {

    }

    public PrivateEvent createPrivateEvent(String name, Boolean open, LocalDate date, Owner owner,
                            PrivatePlace privatePlace, String duration, String city, String type) {
        //Creates a new private event and it saves it inside the database using the EventDAO
        PrivateEvent event = new PrivateEvent(
                name, open, date, owner, privatePlace, duration, city, type);
        eventsDAO.getPrivateEvents().add(event);
        return event;
    }

    public PrivateEvent createPrivateEvent(String name, Boolean open, LocalDate date, Planner planner,
                                           PrivatePlace privatePlace, String duration, String city, String type) {
        //Same as before but instead of owner this one is organized by the planner
        PrivateEvent event = new PrivateEvent(
                name, open, date, planner, privatePlace, duration, city, type);
        eventsDAO.getPrivateEvents().add(event);
        return event;
    }

    public PublicEvent createPublicEvent(String eventName, Boolean open, LocalDate date, Planner planner,
                                         PublicPlace publicPlace, String duration, String city, String eventType) {
        //Creates a new public event and it saves it inside the database using the EventDAO
        PublicEvent event = new PublicEvent(
                eventName, open, date, planner, publicPlace, duration, city, eventType);
        eventsDAO.getPublicEvents().add(event);
        return event;
    }
}
