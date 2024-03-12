package main.BusinessLogic;
import main.DAO.UserDAO;
import main.DomainModel.User;
import main.Interface.UserInterface;

import java.time.LocalDate;
import java.util.Objects;

public class UserController extends InputController {

    private final User user;
    private final UserDAO userDAO;
    private final EventController eventController;
    private final UserInterface userInterface;
    private UserChoices.UserActions userActions;

    public UserController(String user, EventController eventController) {
        this.eventController = eventController;
        userDAO = new UserDAO(user);
        this.user = userDAO.getUser();
        userInterface = new UserInterface();
        userActions = null;

    }

    public void userFunctions() {
        while(!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            userInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            switch (basicUserOptions) {
                case SeeInfo:
                    userInterface.printUserInfo(user);
                    break;
                case SeeEventsMenu:
                    eventsManagement();
                    break;
                case Exit:
                    userInterface.logOut();
                    break;
                default:
                    break;
            }
        }
    }

    public void eventsManagement() {
        boolean quitMenu = false;
        while(!quitMenu) {
            userInterface.basicEventsInterface();
            userActions = getUserInput();
            switch (userActions) {
                case SeeAllEvents:
                    userInterface.printPrivateEvents(eventController.getPrivateEvents());
                    userInterface.printPublicEvents(eventController.getPublicEvents());
                    break;
                case FilterEvents:
                    LocalDate date = getDateFilter();
                    userInterface.printPublicEvents(eventController.getPublicEventsFiltered(date));
                    userInterface.printPrivateEvents(eventController.getPrivateEventsFiltered(date));
                    break;
                case Exit:
                    quitMenu = true;
                    break;
            }
        }
    }

    public UserChoices.UserActions getUserInput() {
        userActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.MusicianActions.values().length) {
            userActions = UserChoices.UserActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            userActions = null;
        }
        input = 0;
        return userActions;
    }


}
