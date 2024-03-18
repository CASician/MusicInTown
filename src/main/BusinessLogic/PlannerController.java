package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DAO.PlannerDAO;
import main.DomainModel.*;
import main.Interface.OwnerInterface;
import main.Interface.PlannerInterface;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class PlannerController extends InputController {

    private UserChoices.OwnerPlannerActions plannerActions;
    protected EventController eventController;
    private final PlannerInterface plannerInterface;
    protected PlannerDAO plannerDAO;
    private final Planner planner;
    protected PlacesController placesController;

    public PlannerController(String username, EventController eventController, PlacesController placesController) {
        this.eventController = eventController;
        plannerDAO = new PlannerDAO();
        this.planner = plannerDAO.getPlanner(username);
        plannerInterface = new PlannerInterface();
        this.placesController = placesController;
    }

    public void plannerFunctions() {
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
            }
        }
    }

    public void eventsManagement() {
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

    private PrivatePlace getPrivatePlace() {
        plannerInterface.getId();
        PrivatePlace privatePlace = null;
        privatePlace = placesController.getPrivatePlace(getId());
        if(privatePlace == null) {
            plannerInterface.tryAgain();
            privatePlace = getPrivatePlace();
        }
        return privatePlace;
    }

    public void createPublicEvent() {
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
        plannerInterface.getId();
        PublicPlace publicPlace = null;
        publicPlace = placesController.getPublicPlace(getId());
        if(publicPlace == null) {
            plannerInterface.tryAgain();
            getPrivatePlace();
        }
        return publicPlace;
    }

    public UserChoices.OwnerPlannerActions getPlannerInput() {
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
