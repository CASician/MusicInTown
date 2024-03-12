package main.BusinessLogic;

import main.DAO.OwnerDAO;
import main.DomainModel.Owner;
import main.DomainModel.PrivatePlace;
import main.Interface.OwnerInterface;

import java.util.Objects;

public class OwnerController extends InputController {
    private UserChoices.OwnerActions ownerActions;
    private PrivatePlace privatePlace;
    private OwnerInterface ownerInterface;
    private OwnerDAO ownerDAO;
    private Owner owner;
    private PrivatePlacesController privatePlacesController;

    public OwnerController(String owner) {
        ownerInterface = new OwnerInterface();
        privatePlacesController = new PrivatePlacesController(owner);
        privatePlace = privatePlacesController.getPrivatePlace();
        ownerDAO = new OwnerDAO(owner, privatePlace);
        this.owner = ownerDAO.getOwner();
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
