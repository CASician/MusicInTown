package main.BusinessLogic;

import main.DAO.AccessDAO;
import main.Interface.AccessInterface;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class AccessController {
    private final AccessDAO accessDAO;
    private final Scanner scanner;
    private final AccessInterface accessInterface;
    public String email;

    public AccessController() {
        this.accessDAO = new AccessDAO();
        this.scanner = new Scanner(System.in);
        this.accessInterface = new AccessInterface();
        this.email = null;
    }

    public String start() {
        boolean start = true;
        String input = null;

        while (start) {
            accessInterface.firstView();
            input = scanner.nextLine();

            if (Objects.equals(input, "l") || Objects.equals(input, "e")) {
                start = false;
            }
        }
        return input;
    }


    public String login() {
        /*
        Comunica con il DAO, se password ed username sono presenti allora
        crea l'oggetto desiderato nel Domain Model e ritorna true
        */
        HashMap<String, String> input = new HashMap<>();
        String userType = null;

        accessInterface.askEmail();
        email = scanner.nextLine();
        input.put("email" , email);
        accessInterface.askPassword();
        input.put("password", scanner.nextLine());

        userType = accessDAO.login(input);

        if(userType != null) {
            accessInterface.success(email);
        }
        else accessInterface.failure();

        return userType;
    }

}
