package main.BusinessLogic;

import main.Interface.AccessInterface;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public abstract class InputController {
    Scanner scanner;
    int choice;

    AccessInterface accessInterface;

    public InputController() {
        scanner = new Scanner(System.in);
        choice = 0;
        accessInterface = new AccessInterface();
    }

    public UserActions getFirstInput() {
        accessInterface.firstView();
        choice = scanner.nextInt();
        UserActions selectedCategory = null;

        if (choice >= 0 && choice < UserActions.values().length) {
            selectedCategory = UserActions.values()[choice];
        } else {
            accessInterface.invalidChoice();
            if(tryAgain())
                selectedCategory = getFirstInput();
        }
        choice = 0;
        return selectedCategory;
    }

    public String getEmailInput() {
        accessInterface.askEmail();
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        accessInterface.askPassword();
        return scanner.nextLine();
    }

    public boolean tryAgain() {
        accessInterface.tryAgain();
        return Objects.equals(scanner.nextInt(), 0);
    }
}
