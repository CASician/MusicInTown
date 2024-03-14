package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DomainModel.Owner;
import main.Interface.OwnerInterface;

import java.time.LocalDate;
import java.util.Objects;

public class OwnerController extends InputController {
    private UserChoices.OwnerActions ownerActions;
    protected EventController eventController;
    private final OwnerInterface ownerInterface;
    protected OwnerDAO ownerDAO;
    private final Owner owner;
    protected PlacesController placesController;

    public OwnerController(String owner, EventController eventController, PlacesController placesController) {
        ownerInterface = new OwnerInterface();
        ownerDAO = new OwnerDAO(owner, placesController);
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
        ownerInterface.getEventName();
        String eventName = getString();
        ownerInterface.getOpenEvent();
        Boolean open = getBoolean();
        ownerInterface.getExactDate();
        LocalDate date = getDate();
        ownerInterface.getDuration();
        String duration = getInteger() + " giorni";
        String city = owner.getPlace().getCity();
        ownerInterface.getEventType();
        String eventType = getString();
        eventController.createEvent(eventName, open, date, owner, owner.getPlace(), duration, city, eventType);
    }

    public UserChoices.OwnerActions getOwnerInput () {
        ownerActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.OwnerActions.values().length) {
            ownerActions = UserChoices.OwnerActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            ownerActions = null;
        }
        input = 0;
        return ownerActions;
    }
}
