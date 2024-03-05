package main.BusinessLogic;

import main.Interface.AccessInterface;
import main.Interface.BasicUserInterface;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class InputController {
    Scanner scanner;
    UserActions.BasicUser basicUserOptions;
    int input;
    BasicUserInterface basicUserInterface;
    AccessInterface accessInterface;

    public InputController() {
        scanner = new Scanner(System.in);
        accessInterface = new AccessInterface();
        basicUserInterface = new BasicUserInterface();
    }

    public UserActions getFirstInput() {
        accessInterface.firstView();
        input = scanner.nextInt();
        UserActions selectedCategory = null;

        if (input >= 0 && input < UserActions.values().length) {
            selectedCategory = UserActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            if(tryAgain())
                selectedCategory = getFirstInput();
        }
        input = 0;
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

    public UserActions.BasicUser firstMenuInput() {
        basicUserOptions = null;
        input = scanner.nextInt();
        if (input >= 0 && input < UserActions.BasicUser.values().length) {
            basicUserOptions = UserActions.BasicUser.values()[input];
        } else {
            accessInterface.invalidChoice();
            basicUserOptions = null;
        }
        input = 0;
        return basicUserOptions;
    }
    public int getId() {
        basicUserInterface.eventId();
        return scanner.nextInt();
    }

    public int getEventType() {
        basicUserInterface.eventType();
        int input;
        input = scanner.nextInt();
        while(input != 0 && input != 1) {
            basicUserInterface.tryAgainType();
            input = scanner.nextInt();
        }
        return input;
    }

    public LocalDate getDateFilter() {
        basicUserInterface.filter();
        basicUserInterface.getYear();
        int year = scanner.nextInt();
        basicUserInterface.getMonth();
        int month = scanner.nextInt();
        basicUserInterface.getDay();
        int day = scanner.nextInt();

        return LocalDate.of(year, month, day);
    }
}
