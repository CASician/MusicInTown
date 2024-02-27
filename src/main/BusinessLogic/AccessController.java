package main.BusinessLogic;

import main.DAO.AccessDAO;
import main.DomainModel.UserType;
import main.Interface.AccessInterface;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class AccessController extends InputController {
    private final AccessDAO accessDAO;

    public String email;
    UserType userType;

    public AccessController() {
        this.accessDAO = new AccessDAO();
        this.scanner = new Scanner(System.in);
        this.email = null;
        userType = null;
    }


    public UserType login() {
        /*
        Comunica con il DAO, se password ed username sono presenti allora
        crea l'oggetto desiderato nel Domain Model e ritorna true
        */

        HashMap<String, String> input = new HashMap<>();

        input.put("email" , getEmailInput());
        email = input.get("email");
        input.put("password", getPasswordInput());
        userType = accessDAO.login(input);
        boolean loop = true;

        while(loop) {

            if (userType != null) {
                accessInterface.success(email);
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
