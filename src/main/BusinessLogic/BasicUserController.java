package main.BusinessLogic;

import main.DomainModel.PrivatePlace;
import main.Interface.AccessInterface;
import main.Interface.BasicUserInterface;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
* Abstract class that implements all the common methods for the controller
*/

public abstract class BasicUserController {
    Scanner scanner;
    protected PlacesController placesController;
    protected UserChoices.BasicUser basicUserOptions;
    int input;
    protected BasicUserInterface basicUserInterface;
    protected AccessInterface accessInterface;

    public BasicUserController(PlacesController placesController) {
        this.placesController = placesController;
        scanner = new Scanner(System.in);
        accessInterface = new AccessInterface();
        basicUserInterface = new BasicUserInterface();
    }

    public UserChoices getFirstInput() {
        /*
        * Takes the first input, that is the one used to choose between login or exit the app.
        */
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

    public String getUsernameInput() {
        //Asks for email.
        accessInterface.askUsername();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        //Asks for password
        accessInterface.askPassword();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean tryAgain() {
        //Used after a wrong value entered from the user.
        //It displays the try again message and check if the user wants to try a new insertion or quit the menu.
        accessInterface.tryAgain();
        scanner = new Scanner(System.in);
        return Objects.equals(scanner.nextInt(), 0);
    }

    public UserChoices.BasicUser firstMenuInput() {
        /*
         * Takes the first input, that is the one common between all the UserTypes.
         * It returns the value selected from the user input.
         */
        basicUserOptions = null;
        scanner = new Scanner(System.in);
        input = scanner.nextInt();
        if (input >= 0 && input < UserChoices.BasicUser.values().length) {
            basicUserOptions = UserChoices.BasicUser.values()[input];
        } else {
            accessInterface.invalidChoice();
            if(!tryAgain()) {
                basicUserOptions = UserChoices.BasicUser.Exit;
            }
            else {
                basicUserInterface.newValue();
                basicUserOptions = firstMenuInput();
            }
        }
        input = 0;
        return basicUserOptions;
    }

    /* public List<PrivatePlace> getPrivatePlaces() throws SQLException {
        return placesController.getPrivatePlaces();
    }
     */

    public int getId() {
        //Take the input for an ID
        basicUserInterface.eventId();
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int getEventType() {
        //Ask the type of event we're looking for (private or public)
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
        //Wrapper of the function to get the date filter
        basicUserInterface.filter();
        return getDate();
    }

    public LocalDate getDate() {
        //Get the input for a specific date and check if it's correct
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
        //Get an integer
        scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public String getString(){
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getEventName() {
        //Get an event Name
        basicUserInterface.getEventName();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Boolean getBoolean() {
        //Get a boolean
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
        //Ask for the info of an event (string)
        basicUserInterface.getEventInfo();
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int privatePublicEvent() {
        //Ask if an event that is going to be created is public or private
        int privateEvent;
        basicUserInterface.isPrivatePublicEvent();
        scanner = new Scanner(System.in);
        privateEvent = Integer.parseInt(scanner.nextLine());
        if(privateEvent != 0 && privateEvent != 1) {
            basicUserInterface.tryAgain();
            privateEvent = privatePublicEvent();
        }
        return privateEvent;
    }
}
