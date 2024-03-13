package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;
import main.Interface.OwnerInterface;

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
        while(!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            ownerInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            switch (basicUserOptions) {
                case SeeInfo:
                    ownerInterface.printOwnerInfo(owner);
                    break;
                case SeeEventsMenu:
                    ownerInterface.eventsManagement();
                    break;
                case Exit:
                    ownerInterface.logOut();
                    break;
                default:
                    break;
            }
        }
    }
}
