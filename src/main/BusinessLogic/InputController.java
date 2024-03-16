package main.BusinessLogic;

import main.Interface.AccessInterface;
import main.Interface.BasicUserInterface;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public abstract class InputController {
    Scanner scanner;
    UserChoices.BasicUser basicUserOptions;
    int input;
    BasicUserInterface basicUserInterface;
    AccessInterface accessInterface;

    public InputController() {
        scanner = new Scanner(System.in);
        accessInterface = new AccessInterface();
        basicUserInterface = new BasicUserInterface();
    }

    public UserChoices getFirstInput() {
        accessInterface.firstView();
        input = scanner.nextInt();
        UserChoices selectedCategory = null;

        if (input >= 0 && input < UserChoices.values().length) {
            selectedCategory = UserChoices.values()[input];
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

    public UserChoices.BasicUser firstMenuInput() {
        basicUserOptions = null;
        input = scanner.nextInt();
        if (input >= 0 && input < UserChoices.BasicUser.values().length) {
            basicUserOptions = UserChoices.BasicUser.values()[input];
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
        return getDate();
    }

    public LocalDate getDate() {
        basicUserInterface.getYear();
        int year = scanner.nextInt();
        basicUserInterface.getMonth();
        int month = scanner.nextInt();
        basicUserInterface.getDay();
        int day = scanner.nextInt();
        LocalDate date;
        try { date = LocalDate.of(year, month, day); }
        catch (DateTimeException error) {
            basicUserInterface.tryAgain();
            date = getDate();
        }
        if(date.isBefore(LocalDate.now())) {
            basicUserInterface.tryAgain();
            date = getDate();
        }
        return date;
    }

    public int getInteger() {
        return scanner.nextInt();
    }

    public String getEventName() {
        basicUserInterface.getEventName();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Boolean getBoolean() {
        Boolean open;
        int insertion = scanner.nextInt();
        if(insertion == 0) {
            open = true;
        } else if (insertion == 1) {
            open = false;
        } else {
            tryAgain();
            open = getBoolean();
        }
        return open;
    }

    public String getEventInfo() {
        basicUserInterface.getEventInfo();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int privatePublicEvent() {
        int privateEvent;
        scanner = new Scanner(System.in);
        privateEvent = scanner.nextInt();
        if(privateEvent != 0 && privateEvent != 1) {
            basicUserInterface.tryAgain();
            privatePublicEvent();
        }
        return privateEvent;
    }
}
