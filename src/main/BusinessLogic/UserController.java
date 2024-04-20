package main.BusinessLogic;
import main.DAO.UserDAO;
import main.DomainModel.User;
import main.Interface.UserInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/*
* Class that controls all the actions of the basic User.
*/
public class UserController extends BasicUserController {

    private final User user;
    private final EventController eventController;
    private final UserInterface userInterface;
    UserChoices.UserActions userActions;

    public UserController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        this.eventController = eventController;
        this.user = UserDAO.getUser(username);
        userInterface = new UserInterface();
        userActions = null;

    }

    public void userFunctions() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the first menu.
         * The first menu is the one about showing user and places info, quitting the app or entering the events menu
        */
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
                case SeeAllPlaces:
                    userInterface.printPrivatePlaces(placesController.getPrivatePlaces());
                    userInterface.printPublicPlaces(placesController.getPublicPlaces());
                    break;
                default:
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
        //Get the inputs from the Owner and returns the specific action set inside the UserChoices Enumeration
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
