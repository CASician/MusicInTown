package main.BusinessLogic;

import main.DAO.AccessDAO;
import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Scanner;

/*
* Class used to access the application. It controls that username and password are registered inside database.
* Then it returns the UserType to set up all the project dependencies and usages.
*/

public class AccessController extends BasicUserController {
    private final AccessDAO accessDAO;

    public String username;
    UserType userType;
    HashMap<String, String> input;

    public AccessController() {
        super(new PlacesController());
        accessDAO = new AccessDAO();
        scanner = new Scanner(System.in);
        username = null;
        userType = null;
    }

    public UserType login() {
        /*
        Communicates with DAO to check if user is registered and returns the UserType
        */
        input = new HashMap<>();

        input.put("username" , getUsernameInput());
        username = input.get("username");
        input.put("password", getPasswordInput());
        userType = accessDAO.login(input);
        if (userType != null) {
            accessInterface.success(username);
        } else {
            accessInterface.loginFailure();
            if(tryAgain()) {
                userType = login();
            }
        }
        return userType;
    }

}
