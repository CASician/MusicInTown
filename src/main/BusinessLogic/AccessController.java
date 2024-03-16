package main.BusinessLogic;

import main.DAO.AccessDAO;
import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Scanner;

public class AccessController extends InputController {
    private final AccessDAO accessDAO;

    public String username;
    UserType userType;

    public AccessController() {
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

        HashMap<String, String> input = new HashMap<>();

        input.put("username" , getEmailInput());
        username = input.get("username");
        input.put("password", getPasswordInput());
        userType = accessDAO.login(input);
        boolean loop = true;

        while(loop) {

            if (userType != null) {
                accessInterface.success(username);
                loop = false;
            } else {
                accessInterface.loginFailure();
                if(!tryAgain()) {
                    loop = false;
                }
            }
        }

        return userType;
    }

}
