package main.BusinessLogic;

import main.DAO.*;
import main.DomainModel.*;
import main.Interface.BasicUserInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/*
* Class that implements all the methods to manage the events.
* It communicates with the EventDAO and generates/modify public and private events
*/
public class EventController {
    ArrayList<PublicEvent> publicEventsList;
    ArrayList<PrivateEvent> privateEventsList;
    private final BasicUserInterface basicUserInterface;

    public EventController(PlacesController placesController) {
        basicUserInterface = new BasicUserInterface();
    }

    public void subscribePublicEvent(Musician musician, Event event) throws SQLException {
        /*
        * Method used from the musician to subscribe itself to a specific public event between musician ID and event ID.
        * It checks if the event exists and if the musician is already subscribed.
        */
        boolean alreadySubscribed = SubscriptionsDAO.exists(musician, event);

        if(!alreadySubscribed) {
                event.addSubscriber(musician);
                musician.addSubscription(event);
                SubscriptionsDAO.add(musician, event);
        } else {
                basicUserInterface.alreadySubscribed();
        }
    }
    public void subscribePrivateEvent(Musician musician, Event event) throws SQLException {
        /*
         * Method used from the musician to subscribe itself to a specific private event between musician ID and event ID.
         * It checks if the event exists and if the musician is already subscribed.
         */
        boolean found = false;
        boolean alreadySubscribed = false;
        ArrayList<Musician> subscribers;
        // Look for the event and check if it exists.
        privateEventsList = PrivateEventDAO.getAll();
        for (PrivateEvent event_iterator : privateEventsList) {
            if (event.getId() == event_iterator.getId()) {
                found = true;
                // Check if the Musician is not already subscribed
                subscribers = SubscriptionsDAO.getSubscribers(event_iterator);
                for(Musician sub : subscribers){
                    if(sub.getId() == musician.getId()){
                        alreadySubscribed = true;
                        break;
                    }
                }
            }
            break;
        }
        if(!found) {
            basicUserInterface.eventNotFound();
        }
        else {
            if(!alreadySubscribed) {
                event.addSubscriber(musician);
                musician.addSubscription(event);
                SubscriptionsDAO.add(musician, event);
            }
            else {
                basicUserInterface.alreadySubscribed();
            }
        }
    }

    public ArrayList<PublicEvent> getPublicEvents() throws SQLException {
        //Returns a list of all the public events
        if(publicEventsList == null) {
            publicEventsList = PublicEventDAO.getAll();
        }
        return publicEventsList;
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
        if(publicEventsList == null) {
            publicEventsList = PublicEventDAO.getAll();
        }
        for (PublicEvent event : publicEventsList) {
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

    public void notifyEventObservers(Event event) throws SQLException {
        if(event instanceof PublicEvent){
            // call proper municipality and add the event to its list
            // Downcast?
            PublicEvent publicEvent = (PublicEvent) event;
            // Add the event to municipality's array
            Municipality m = MunicipalityDAO.getMunicipality("florence");
            m.propose_event(publicEvent);
            // Add the request in the database
            EventsToBeAcceptedDAO.add(m, publicEvent);
            // Show Results
            System.out.println("Municipality notified successfully! ");
        } else if(event instanceof PrivateEvent){
            // call proper owner and add the event to its list
            // Downcast?
            PrivateEvent privateEvent = (PrivateEvent) event;
            // Add the event to owner's array
            privateEvent.getPlace().getOwner().propose_event(privateEvent);
            // Add the request in the database
            EventsToBeAcceptedDAO.add(privateEvent.getPlace().getOwner(), privateEvent);
            // Show Results
            System.out.println("Owner notified successfully! ");
        }
    }

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
