package main.BusinessLogic;

import main.DAO.PlannerDAO;
import main.DomainModel.*;
import main.Interface.PlannerInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/*
* Class that controls all the actions of the Planner.
*/
public class PlannerController extends BasicUserController {

    UserChoices.OwnerPlannerActions plannerActions;
    private final EventController eventController;
    private final PlannerInterface plannerInterface;
    private final Planner planner;
    private final PlacesController placesController;

    public PlannerController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        this.eventController = eventController;
        this.planner = PlannerDAO.getPlanner(username);
        plannerInterface = new PlannerInterface();
        this.placesController = placesController;
    }

    public void plannerFunctions() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the first menu.
         * The first menu is the one about showing user and places info, quitting the app or entering the events menu
        */
        while (!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            plannerInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            if(planner == null) {
                plannerInterface.loginError();
                basicUserOptions = UserChoices.BasicUser.Exit;
            }
            switch (basicUserOptions) {
                case SeeEventsMenu:
                    eventsManagement();
                    break;
                case Exit:
                    plannerInterface.logOut();
                    break;
                case SeeInfo:
                    plannerInterface.printPlannerInfo(planner);
                    break;
                case SeeAllPlaces:
                    plannerInterface.printPrivatePlaces(placesController.getPrivatePlaces());
                    plannerInterface.printPublicPlaces(placesController.getPublicPlaces());
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
            plannerInterface.eventsInterface();
            plannerActions = getPlannerInput();
            switch (plannerActions) {
                case SeeAllEvents:
                    plannerInterface.printPrivateEvents(eventController.getPrivateEvents());
                    plannerInterface.printPublicEvents(eventController.getPublicEvents());
                    break;
                case FilterEvents:
                    LocalDate date = getDateFilter();
                    plannerInterface.printPrivateEvents(eventController.getPrivateEventsFiltered(date));
                    plannerInterface.printPublicEvents(eventController.getPublicEventsFiltered(date));
                    break;
                case CreateEvent:
                    if(privatePublicEvent() == 0) {
                        createPrivateEvent();
                    }
                    else {
                        createPublicEvent();
                    }
                    break;
                case Exit:
                    quitMenu = true;
                    break;
                case SelectMusician:
                    if(privatePublicEvent() == 0) {
                        selectMusicianPr(privateEventsCreated());
                    }
                    else {
                        selectMusicianPu(publicEventsCreated());
                    }
                    break;
            }
        }
    }

    private void selectMusicianPr(ArrayList<PrivateEvent> privateEvents) {
        if(privateEvents != null) {
            plannerInterface.printPrivateEvents(privateEvents);
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
                if(!found) { plannerInterface.tryAgain(); }
            }
            boolean isMusician = false;
            if(!event.getSubscribers().isEmpty()) {
                isMusician = true;
                for (Musician musician : event.getSubscribers()) {
                    plannerInterface.printMusicianInfo(musician);
                }
            }
            plannerInterface.chooseMusician(isMusician);
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
                            plannerInterface.successChoose();
                            break;
                        }
                    }
                    if(!found) { plannerInterface.tryAgain(); }
                }
            }
        }
        else {
            plannerInterface.noEventsCreated();
        }
    }

    private void selectMusicianPu(ArrayList<PublicEvent> publicEvents) {
        if(publicEvents != null) {
            plannerInterface.printPublicEvents(publicEvents);
            PublicEvent event = null;
            boolean found = false;
            while(!found) {
                int id = getId();
                for (PublicEvent publicEvent : publicEvents) {
                    if(publicEvent.getId() == id) {
                        found = true;
                        event = publicEvent;
                        break;
                    }
                }
                if(!found) { plannerInterface.tryAgain(); }
            }
            boolean isMusician = false;
            if(!event.getSubscribers().isEmpty()) {
                isMusician = true;
                for (Musician musician : event.getSubscribers()) {
                    plannerInterface.printMusicianInfo(musician);
                }
            }
            plannerInterface.chooseMusician(isMusician);
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
                            plannerInterface.successChoose();
                            break;
                        }
                    }
                    if(!found) { plannerInterface.tryAgain(); }
                }
            }
        }
        else {
            plannerInterface.noEventsCreated();
        }
    }

    private ArrayList<PrivateEvent> privateEventsCreated() throws SQLException {
        ArrayList<PrivateEvent> privateEvents = new ArrayList<>();
        ArrayList<PrivateEvent> allPrivateEvents = eventController.getPrivateEvents();
        for (PrivateEvent privateEvent : allPrivateEvents) {
            if (Objects.equals(privateEvent.getPlanner().getName(), this.planner.getName())
            && privateEvent.isOpen()) {
                privateEvents.add(privateEvent);
            }
        }
        if(privateEvents.isEmpty()) {
            privateEvents = null;
        }
        return privateEvents;
    }

    private ArrayList<PublicEvent> publicEventsCreated() throws SQLException {
        ArrayList<PublicEvent> publicEvents = new ArrayList<>();
        ArrayList<PublicEvent> allPublicEvents = eventController.getPublicEvents();
        for (PublicEvent publicEvent : allPublicEvents) {
            if (Objects.equals(publicEvent.getPlanner().getName(), this.planner.getName())
            && publicEvent.isOpen()) {
                publicEvents.add(publicEvent);
            }
        }
        if(publicEvents.isEmpty()) {
            publicEvents = null;
        }
        return publicEvents;
    }

    public void createPrivateEvent() throws SQLException {
        /*
         * Uses the interface to obtain all the infos to create the event.
         * At the end it generates the event withe received info
        */
        PrivateEvent event;
        String eventName = getEventName();
        plannerInterface.getOpenEvent();
        Boolean open = getBoolean();
        plannerInterface.getExactDate();
        LocalDate date = getDate();
        plannerInterface.getDuration();
        String duration = getInteger() + " giorni";
        plannerInterface.printPrivatePlaces(placesController.getPrivatePlaces());
        PrivatePlace privatePlace = getPrivatePlace();
        String city = privatePlace.getCity();
        String eventType = getEventInfo();
        event = eventController.createPrivateEvent(eventName, open, date, planner, privatePlace, duration, city, eventType);
        // The ID is automatically assigned by the DAO
        plannerInterface.privateEventCreated(event);
    }

    public void createPublicEvent() throws SQLException {
        /*
         * Uses the interface to obtain all the infos to create the event.
         * At the end it generates the event withe received info
        */
        PublicEvent event;
        String eventName = getEventName();
        plannerInterface.getOpenEvent();
        Boolean open = getBoolean();
        plannerInterface.getExactDate();
        LocalDate date = getDate();
        plannerInterface.getDuration();
        String duration = getInteger() + " giorni";
        plannerInterface.printPublicPlaces(placesController.getPublicPlaces());
        PublicPlace publicPlace = getPublicPlace();
        String eventType = getEventInfo();
        event = eventController.createPublicEvent(eventName, open, date, planner, publicPlace, duration, publicPlace.getCity(), eventType);
        // The ID is automatically assigned by the DAO
        plannerInterface.publicEventCreated(event);
    }

    private PublicPlace getPublicPlace() throws SQLException {
        //Returns a private place with the givenID
        plannerInterface.getName();
        PublicPlace publicPlace = placesController.getPublicPlace(getString());
        if(publicPlace == null) {
            plannerInterface.tryAgain();
            publicPlace = getPublicPlace();
        }
        return publicPlace;
    }

    private PrivatePlace getPrivatePlace() throws SQLException {
        //Returns a private place with the givenID
        plannerInterface.getName();
        PrivatePlace privatePlace = placesController.getPrivatePlace(getString());
        if(privatePlace == null) {
            plannerInterface.tryAgain();
            privatePlace = getPrivatePlace();
        }
        return privatePlace;
    }

    public UserChoices.OwnerPlannerActions getPlannerInput() {
        //Get the inputs from the Owner and returns the specific action set inside the UserChoices Enumeration
        plannerActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.OwnerPlannerActions.values().length) {
            plannerActions = UserChoices.OwnerPlannerActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            plannerActions = null;
        }
        input = 0;
        return plannerActions;
    }
}
