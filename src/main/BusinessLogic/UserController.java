package main.BusinessLogic;
import main.DAO.UserDAO;
import main.DomainModel.User;
import main.Interface.UserInterface;

import java.time.LocalDate;
import java.util.Objects;

public class UserController extends InputController {

    User user;
    UserDAO userDAO;
    EventController eventController;
    UserInterface userInterface;
    UserChoices.UserActions operation;

    public UserController(String user) {
        eventController = new EventController();
        userDAO = new UserDAO(user);
        this.user = userDAO.getUser();
        userInterface = new UserInterface();
        operation = null;

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
            operation = getUserInput();
            switch (operation) {
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
        operation = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.MusicianActions.values().length) {
            operation = UserChoices.UserActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            operation = null;
        }
        input = 0;
        return operation;
    }


}
