package main.BusinessLogic;
import main.DAO.UserDAO;
import main.DomainModel.User;
import main.Interface.BasicUserInterface;

public class UserController {

    User user;
    UserDAO userDAO;
    EventController eventController;
    BasicUserInterface userInterface;

    public UserController(String user) {
        eventController = new EventController();
        userDAO = new UserDAO(user);
        this.user = userDAO.getUser();
        userInterface = new BasicUserInterface();

    }


}
