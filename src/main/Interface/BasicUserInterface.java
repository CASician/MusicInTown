package main.Interface;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.util.List;

public class BasicUserInterface {

    public void basicInterface() {
        System.out.println("Press: ");
        System.out.println("0 to exit, ");
        System.out.println("1 to see events menu, ");
        System.out.println("2 to see user info, ");
    }

    public void basicEventsInterface() {
        System.out.println("Press: ");
        System.out.println("0 to go back, " );
        System.out.println("1 to filter events, " );
        System.out.println("2 to visualize all events, ");
    }

    public void eventId() {
        System.out.println("Enter the event ID: ");
    }
    public void eventType() {
        System.out.println("Press: ");
        System.out.println("0 if event is private, ");
        System.out.println("1 if event is public");
    }
    public void tryAgainType() {
        System.out.println("Invalid selection try again, ");
        eventType();
    }
    public void eventNotFound() {
        System.out.println("Event not found");
    }
    public void invalidChoice() {
        System.out.println("The choice is invalid");
    }
    public void filter() {System.out.println("Insert the date to see all previous events");}
    public void getYear() {
        System.out.println("Insert year: ");
    }
    public void getMonth() { System.out.println("Insert month: "); }
    public void getDay() { System.out.println("Insert day: "); }

    public void getEventName() {
        System.out.println("Event name: ");
    }

    public void logOut() {
        System.out.println("Logout done,");
        System.out.println("See you soon.");
    }

    public void printPublicEvents(List<PublicEvent> events) {
        if(events != null) {
            for (PublicEvent event : events) {
                System.out.println("--------------------");
                System.out.print("EVENT NAME: " + event.getName());
                System.out.print(", DATE: " + event.getDate());
                System.out.print(", CITY: " + event.getPlace().getCity());
                System.out.print(", ADDRESS: " + event.getPlace().getAddress());
                System.out.print(", DURATION: " + event.getDuration());
                System.out.println(", TYPE: " + event.getType());
            }
        }
    }

    public void tryAgain() {
        System.out.println("Wrong value inserted, try again");
    }

    public void printPrivateEvents(List<PrivateEvent> events) {
        if(events != null) {
            for (PrivateEvent event : events) {
                System.out.println("--------------------");
                System.out.print("EVENT NAME: " + event.getName());
                System.out.print(", DATE: " + event.getDate());
                System.out.print(", CLUB NAME: " + event.getPlace().getName());
                System.out.print(", CITY: " + event.getPlace().getCity());
                System.out.print(", ADDRESS: " + event.getPlace().getAddress());
                System.out.print(", DURATION: " + event.getDuration());
                System.out.println(", TYPE: " + event.getType());
            }
        }
    }

    public void isPrivatePublicEvent() {
        System.out.println("Where will the event take place?");
        System.out.println("insert 0 for private place, 1 for public place");
    }
    public void getOpenEvent() {
        System.out.println("Is the event open?");
        System.out.println("insert 0 for true, 1 for false");
    }

    public void getExactDate() {
        System.out.println("Insert the date of the event: ");
    }

    public void getDuration() {
        System.out.println("Insert the duration of the event in days: ");
    }

    public void getEventType() {
        System.out.println("Insert the type of event: ");
    }

    public void getEventInfo() {
        System.out.println("Insert some short event info: ");
    }

    public void alreadySubscribed() {
        System.out.println("--------------------");
        System.out.println("You are already subscribed to this event");
        System.out.println("--------------------");
    }

    public void loginError() {
        System.out.println("--------------------");
        System.out.println("Database error during Login, the application will be shut down");
        System.out.println("--------------------");
    }

    public void newValue() {
        System.out.println("Insert a new value: ");
    }
}
