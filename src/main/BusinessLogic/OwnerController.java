package main.BusinessLogic;

import main.DAO.EventsToBeAcceptedDAO;
import main.DAO.OwnerDAO;
import main.DomainModel.Event;
import main.DomainModel.Musician;
import main.DomainModel.Owner;
import main.DomainModel.PrivateEvent;
import main.Interface.OwnerInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Boolean.TRUE;

/*
* Class that controls all the actions of the Owner.
*/
public class OwnerController extends BasicUserController{
    UserChoices.OwnerPlannerActions ownerActions;
    private final EventController eventController;
    private final OwnerInterface ownerInterface;
    private final Owner owner;
    private final PlacesController placesController;

    public OwnerController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        ownerInterface = new OwnerInterface();
        this.owner = OwnerDAO.getOwner(username);
        this.placesController = placesController;
        this.eventController = eventController;
    }

    public void ownerFunctions() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the first menu.
         * The first menu is the one about showing user and places info, quitting the app or entering the events menu
        */
        while (!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            ownerInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            switch (basicUserOptions) {
                case SeeInfo:
                    ownerInterface.printOwnerInfo(owner);
                    break;
                case SeeEventsMenu:
                    eventsManagement();
                    break;
                case Exit:
                    ownerInterface.logOut();
                    break;
                case SeeAllPlaces:
                    ownerInterface.printPrivatePlaces(placesController.getPrivatePlaces());
                    ownerInterface.printPublicPlaces(placesController.getPublicPlaces());
                    break;
                default:
                    break;
            }
        }
    }

    public void eventsManagement() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the second menu.
         * The second menu is the one about managing and showing all the events.
        */
        boolean quitMenu = false;
        while (!quitMenu) {
            ownerInterface.eventsInterface();
            ownerActions = getOwnerInput();
            switch (ownerActions) {
                case Exit:
                    quitMenu = true;
                    break;
                case SeeAllEvents:
                    ownerInterface.printPrivateEvents(eventController.getPrivateEvents());
                    ownerInterface.printPublicEvents(eventController.getPublicEvents());
                    break;
                case FilterEvents:
                    LocalDate date = getDateFilter();
                    ownerInterface.printPrivateEvents(eventController.getPrivateEventsFiltered(date));
                    ownerInterface.printPublicEvents(eventController.getPublicEventsFiltered(date));
                    break;
                case CreateEvent:
                    createEvent();
                case AcceptEvents:
                    acceptEvent();
                    break;
                case SelectMusician:
                    selectMusicianPr(privateEventsCreated());
                    break;
            }
        }
    }

    private void selectMusicianPr(ArrayList<PrivateEvent> privateEvents) throws SQLException {
        if(privateEvents != null) {
            ownerInterface.printPrivateEvents(privateEvents);
            PrivateEvent event = null;
            boolean found = false;
            while(!found) {
                int id = getId();
                for (PrivateEvent privateEvent : privateEvents) {
                    if(privateEvent.getId() == id) {
                        found = true;
                        event = privateEvent;
                        break;
                    }
                }
                if(!found) { ownerInterface.tryAgain(); }
            }
            boolean isMusician = false;
            if(!event.getSubscribers().isEmpty()) {
                isMusician = true;
                for (Musician musician : event.getSubscribers()) {
                    ownerInterface.printMusicianInfo(musician);
                }
            }
            ownerInterface.chooseMusician(isMusician);
            found = false;
            Musician musician = null;
            if(!event.getSubscribers().isEmpty()) {
                while (!found) {
                    int id = getId();
                    for (Musician musicianFound: event.getSubscribers()) {
                        if(musicianFound.getId() == id) {
                            found = true;
                            musician = musicianFound;
                            event.setOpen(false);
                            ownerInterface.successChoose();
                            break;
                        }
                    }
                    if(!found) { ownerInterface.tryAgain(); }
                }
            }
        }
        else {
            ownerInterface.noEventsCreated();
        }
    }

    private ArrayList<PrivateEvent> privateEventsCreated() throws SQLException {
        ArrayList<PrivateEvent> privateEvents = new ArrayList<>();
        ArrayList<PrivateEvent> allPrivateEvents = eventController.getPrivateEvents();
        for (PrivateEvent privateEvent : allPrivateEvents) {
            if (Objects.equals(privateEvent.getPlanner().getName(), this.owner.getName())
                    && privateEvent.isOpen()) {
                privateEvents.add(privateEvent);
            }
        }
        if(privateEvents.isEmpty()) {
            privateEvents = null;
        }
        return privateEvents;
    }

    public void createEvent() throws SQLException {
        /*
        * Uses the interface to obtain all the infos to create the event.
        * At the end it generates the event withe received info
        */
        PrivateEvent event;
        String eventName = getEventName();
        ownerInterface.getOpenEvent();
        Boolean open = getBoolean();
        ownerInterface.getExactDate();
        LocalDate date = getDate();
        ownerInterface.getDuration();
        String duration = getInteger() + " giorni";
        String city = owner.getPlace().getCity();
        String eventType = getEventInfo();
        event = eventController.createPrivateEvent(eventName, open, date, owner, owner.getPlace(),
                duration, city, eventType);
        ownerInterface.eventCreated(event);
    }

    public UserChoices.OwnerPlannerActions getOwnerInput () {
        //Get the inputs from the Owner and returns the specific action set inside the UserChoices Enumeration
        ownerActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.OwnerPlannerActions.values().length) {
            ownerActions = UserChoices.OwnerPlannerActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            ownerActions = null;
        }
        input = 0;
        return ownerActions;
    }

    private void acceptEvent() throws SQLException {
        // Create object to be removed and the list that will be used
        PrivateEvent toBeRemoved = null;
        ArrayList<PrivateEvent> events = owner.getEventsToBeAccepted();

        if (!events.isEmpty()) {
            // print events to be accepted
            ownerInterface.printPrivateEvents(events);

            // take input as id of the event
            System.out.println("---------------");
            System.out.println("Select the ID of the event you want to accept: ");
            input = getInteger();

            // search for the requested event
            if (input >= 0) {
                for (PrivateEvent event: events){
                    if (event.getId() == input){
                        toBeRemoved = event;
                        // set the accepted field to true
                        event.setAccepted(TRUE);
                        // remove event from table in database
                        EventsToBeAcceptedDAO.delete(event.getId());
                        // Show results
                        System.out.println("Event ACCEPTED!");
                    }
                }
                // remove event from array
                owner.remove_event(toBeRemoved);
            } else {
                accessInterface.invalidChoice();
            }
        } else { // The array is empty
            System.out.println("No Events to be accepted. ");
        }
    }
}
