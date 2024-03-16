package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DomainModel.Owner;
import main.DomainModel.PrivateEvent;
import main.Interface.OwnerInterface;

import java.time.LocalDate;
import java.util.Objects;

public class OwnerController extends InputController {
    private UserChoices.OwnerPlannerActions ownerActions;
    protected EventController eventController;
    private final OwnerInterface ownerInterface;
    protected OwnerDAO ownerDAO;
    private final Owner owner;
    protected PlacesController placesController;

    public OwnerController(String username, EventController eventController, PlacesController placesController) {
        ownerInterface = new OwnerInterface();
        ownerDAO = new OwnerDAO(username, placesController);
        this.owner = ownerDAO.getOwner();
        this.placesController = placesController;
        this.eventController = eventController;
    }

    public void ownerFunctions() {
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
                default:
                    break;
            }
        }
    }

    public void eventsManagement() {
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
            }
        }
    }

    public void createEvent() {
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
        event = eventController.createPrivateEvent(eventName, open, date, owner, owner.getPlace(), duration, city, eventType);
        //TODO: set the event ID
        ownerInterface.eventCreated(event);
    }

    public UserChoices.OwnerPlannerActions getOwnerInput () {
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
}
