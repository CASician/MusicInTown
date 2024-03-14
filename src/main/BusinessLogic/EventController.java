package main.BusinessLogic;

import main.DAO.EventsDAO;
import main.DomainModel.*;
import main.Interface.BasicUserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
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
        publicEvent = null;
        publicEvents = eventsDAO.getPublicEvents();
        for (PublicEvent event : publicEvents) {
            if (event.getId() == eventId) {
                publicEvent = event;
                found = true;
                break;
            }
        }
        if(!found) {
            basicUserInterface.eventNotFound();
        }
        else {
            publicEvent.addSubscription(musicianName, musicianId);
        }
        return publicEvent;
    }

    public PrivateEvent subscribePrivateEvent(String musicianName, int musicianId, int eventId) {
        boolean found = false;
        privateEvent = null;
        privateEvents = eventsDAO.getPrivateEvents();
        for (PrivateEvent event : privateEvents) {
            if (event.getId() == eventId) {
                privateEvent = event;
                found = true;
                break;
            }
        }
        if(!found) {
            basicUserInterface.eventNotFound();
        }
        else {
            privateEvent.addSubscription(musicianName, musicianId);
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


}
