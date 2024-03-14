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

    public void logOut() {
        System.out.println("Logout done,");
        System.out.println("See you soon.");
    }

    public void printPublicEvents(List<PublicEvent> events) {
        if(events != null) {
            System.out.println("--------------------");
            for (PublicEvent event : events) {
                System.out.print("Event Name: " + event.getName());
                System.out.println(", ID: " + event.getId());
                System.out.print("Date: " + event.getDate());
                System.out.print(", City: " + event.getPlace().getCity());
                System.out.print(", Address: " + event.getPlace().getAddress());
                System.out.print(", Duration: " + event.getDuration());
                System.out.println(", Type: " + event.getType());
                System.out.println("--------------------");
            }
        }
    }

    public void printPrivateEvents(List<PrivateEvent> events) {
        if(events != null) {
            System.out.println("--------------------");
            for (PrivateEvent event : events) {
                System.out.print("Event Name: " + event.getName());
                System.out.println(", ID: " + event.getId());
                System.out.print("Date: " + event.getDate());
                System.out.print(", City: " + event.getPlace().getCity());
                System.out.print(", Address: " + event.getPlace().getAddress());
                System.out.print(", Duration: " + event.getDuration());
                System.out.println(", Type: " + event.getType());
                System.out.println("--------------------");
            }
        }
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
}
