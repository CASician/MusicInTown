package main.BusinessLogic;

import main.Interface.AccessInterface;
import main.Interface.BasicUserInterface;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public abstract class InputController {
    JOptionPane jOptionPane;
    Scanner scanner;
    UserChoices.BasicUser basicUserOptions;
    int input;
    BasicUserInterface basicUserInterface;
    AccessInterface accessInterface;

    public InputController() {
        jOptionPane = new JOptionPane();
        scanner = new Scanner(System.in);
        accessInterface = new AccessInterface();
        basicUserInterface = new BasicUserInterface();
    }

    public UserChoices getFirstInput() {
        accessInterface.firstView();
        input = Integer.parseInt(scanner.nextLine());
        UserChoices selectedCategory = null;

        if (input >= 0 && input < UserChoices.values().length) {
            selectedCategory = UserChoices.values()[input];
        } else {
            accessInterface.invalidChoice();
            if(tryAgain()) {
                scanner.nextLine();
                selectedCategory = getFirstInput();
            }
        }
        input = 0;
        return selectedCategory;
    }

    public String getEmailInput() {
        accessInterface.askEmail();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        accessInterface.askPassword();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean tryAgain() {
        accessInterface.tryAgain();
        scanner = new Scanner(System.in);
        return Objects.equals(scanner.nextInt(), 0);
    }

    public UserChoices.BasicUser firstMenuInput() {
        basicUserOptions = null;
        scanner = new Scanner(System.in);
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
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int getEventType() {
        basicUserInterface.eventType();
        int input;
        scanner = new Scanner(System.in);
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
        scanner = new Scanner(System.in);
        basicUserInterface.getYear();
        int year = Integer.parseInt(scanner.nextLine());
        basicUserInterface.getMonth();
        int month = Integer.parseInt(scanner.nextLine());
        basicUserInterface.getDay();
        int day = Integer.parseInt(scanner.nextLine());
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
        scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public String getEventName() {
        basicUserInterface.getEventName();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Boolean getBoolean() {
        Boolean open;
        scanner = new Scanner(System.in);
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
        basicUserInterface.isPrivatePublicEvent();
        scanner = new Scanner(System.in);
        privateEvent = Integer.parseInt(scanner.nextLine());
        if(privateEvent != 0 && privateEvent != 1) {
            basicUserInterface.tryAgain();
            privatePublicEvent();
        }
        return privateEvent;
    }
}
