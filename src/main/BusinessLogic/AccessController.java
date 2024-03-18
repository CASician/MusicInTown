package main.BusinessLogic;

import main.DAO.AccessDAO;
import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Scanner;

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
        Comunica con il DAO, se password ed username sono presenti allora
        crea l'oggetto desiderato nel Domain Model e ritorna true
        */
        input = new HashMap<>();

        input.put("username" , getEmailInput());
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
