package main.BusinessLogic;

import main.DAO.PlannerDAO;
import main.DomainModel.*;
import main.Interface.PlannerInterface;

import java.time.LocalDate;
import java.util.Objects;

/*
* Class that controls all the actions of the Planner.
*/
public class PlannerController extends BasicUserController {

    UserChoices.OwnerPlannerActions plannerActions;
    private final EventController eventController;
    private final PlannerInterface plannerInterface;
    private final PlannerDAO plannerDAO;
    private final Planner planner;
    private final PlacesController placesController;

    public PlannerController(String username, EventController eventController, PlacesController placesController) {
        super(placesController);
        this.eventController = eventController;
        plannerDAO = new PlannerDAO();
        this.planner = plannerDAO.getPlanner(username);
        plannerInterface = new PlannerInterface();
        this.placesController = placesController;
    }

    public void plannerFunctions() {
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
                    plannerInterface.printPrivatePlaces(placesController.getPrivatePlacesList());
                    plannerInterface.printPublicPlaces(placesController.getPublicPlacesList());
                    break;
            }
        }
    }

    public void eventsManagement() {
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
            }
        }
    }

    public void createPrivateEvent() {
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
        PrivatePlace privatePlace = getPrivatePlace();
        String city = privatePlace.getCity();
        String eventType = getEventInfo();
        event = eventController.createPrivateEvent(eventName, open, date, planner, privatePlace, duration, city, eventType);
        //TODO: set the event ID
        plannerInterface.privateEventCreated(event);
    }

    public void createPublicEvent() {
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
        PublicPlace publicPlace = getPublicPlace();
        String eventType = getEventInfo();
        event = eventController.createPublicEvent(eventName, open, date, planner, publicPlace, duration, publicPlace.getCity(), eventType);
        //TODO: set the event ID
        plannerInterface.publicEventCreated(event);
    }

    private PublicPlace getPublicPlace() {
        //Returns a private place with the givenID
        plannerInterface.getId();
        PublicPlace publicPlace = null;
        publicPlace = placesController.getPublicPlace(getId());
        if(publicPlace == null) {
            plannerInterface.tryAgain();
            getPrivatePlace();
        }
        return publicPlace;
    }

    private PrivatePlace getPrivatePlace() {
        //Returns a private place with the givenID
        plannerInterface.getId();
        PrivatePlace privatePlace = null;
        privatePlace = placesController.getPrivatePlace(getId());
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
