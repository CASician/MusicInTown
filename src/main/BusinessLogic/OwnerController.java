package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DomainModel.Owner;
import main.DomainModel.PrivateEvent;
import main.Interface.OwnerInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/*
* Class that controls all the actions of the Owner.
*/
public class OwnerController extends BasicUserController {
    UserChoices.OwnerPlannerActions ownerActions;
    private final EventController eventController;
    private final OwnerInterface ownerInterface;
    private final OwnerDAO ownerDAO;
    private final Owner owner;
    private final PlacesController placesController;

    public OwnerController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        ownerInterface = new OwnerInterface();
        ownerDAO = new OwnerDAO();
        this.owner = ownerDAO.getOwner(username);
        this.placesController = placesController;
        this.eventController = eventController;
    }

    public void ownerFunctions() {
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
                    ownerInterface.printPrivatePlaces(placesController.getPrivatePlacesList());
                    ownerInterface.printPublicPlaces(placesController.getPublicPlacesList());
                    break;
                default:
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
}
