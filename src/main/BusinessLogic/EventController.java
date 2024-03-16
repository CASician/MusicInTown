package main.BusinessLogic;

import main.DAO.EventsDAO;
import main.DomainModel.*;
import main.Interface.BasicUserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EventController implements Subject {
    PublicEvent publicEvent;
    PrivateEvent privateEvent;
    EventsDAO eventsDAO;
    List<PublicEvent> publicEvents;
    List<PrivateEvent> privateEvents;
    BasicUserInterface basicUserInterface;

    public EventController(PlacesController placesController) {
        eventsDAO = new EventsDAO(placesController);
        basicUserInterface = new BasicUserInterface();
    }

    public PublicEvent subscribePublicEvent(String musicianName, int musicianId, int eventId) {
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
        if(publicEvents == null) {
            publicEvents = eventsDAO.getPublicEvents();
        }
        return publicEvents;
    }

    public List<PrivateEvent> getPrivateEvents() {
        if(privateEvents == null) {
            privateEvents = eventsDAO.getPrivateEvents();
        }
        return privateEvents;
    }

    public List<PrivateEvent> getPrivateEventsFiltered(LocalDate date) {
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
        PrivateEvent event = new PrivateEvent(
                name, open, date, owner, privatePlace, duration, city, type);
        eventsDAO.getPrivateEvents().add(event);
        return event;
    }

    public PrivateEvent createPrivateEvent(String name, Boolean open, LocalDate date, Planner planner,
                                           PrivatePlace privatePlace, String duration, String city, String type) {
        PrivateEvent event = new PrivateEvent(
                name, open, date, planner, privatePlace, duration, city, type);
        eventsDAO.getPrivateEvents().add(event);
        return event;
    }

    public PublicEvent createPublicEvent(String eventName, Boolean open, LocalDate date, Planner planner,
                                         PublicPlace publicPlace, String duration, String city, String eventType) {
        PublicEvent event = new PublicEvent(
                eventName, open, date, planner, publicPlace, duration, city, eventType);
        eventsDAO.getPublicEvents().add(event);
        return event;
    }
}
