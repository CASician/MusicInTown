package main.Interface;

import main.DomainModel.Musician;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.util.List;

public class MusicianInterface extends BasicUserInterface {

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("3 to see event subscriptions, ");
        System.out.println("4 to do an event subscription. ");
    }

    //Function for testing
    public void printMusicianInfo(Musician musician) {
        System.out.println("--------------------");
        System.out.println("Name: " + musician.getName());
        System.out.println("Email: " + musician.getEmail());
        System.out.println("Genre: " + musician.getGenre());
        System.out.println("Component Number: " + musician.getComponentNumb());
        System.out.println("City: " + musician.getCity());
        System.out.println("--------------------");

    }

    public void publicSubscriptionDone(PublicEvent event) {
        if(event != null) {
            System.out.println("--------------------");
            System.out.println("Subscription completed, Event info:");
            System.out.print("Private Event Name: " + event.getName());
            System.out.print("Date: " + event.getDate());
            System.out.print(", City: " + event.getPlace().getCity());
            System.out.print(", Address: " + event.getPlace().getAddress());
            System.out.println(", Type: " + event.getName());
            System.out.println("--------------------");
        }
    }

    public void privateSubscriptionDone(PrivateEvent event) {
        if(event != null) {
            System.out.println("--------------------");
            System.out.println("Subscription completed, Event info:");
            System.out.print("Private Event Name: " + event.getName());
            System.out.print("Date: " + event.getDate());
            System.out.print(", City: " + event.getPlace().getCity());
            System.out.print(", Address: " + event.getPlace().getAddress());
            System.out.println(", Type: " + event.getName());
            System.out.println("--------------------");
        }
    }

    public void getSubscriptions(List<PublicEvent> publicEvents, List<PrivateEvent> privateEvents) {
        System.out.println("--------------------");
        System.out.println("List of events you're subscribed to: ");
        if(!publicEvents.isEmpty()) {
            printPublicEvents(publicEvents);
        }
        if(!privateEvents.isEmpty()) {
            printPrivateEvents(privateEvents);
        }
        if(publicEvents.isEmpty() && privateEvents.isEmpty()) {
            System.out.println("There are no event subscriptions");
        }
        System.out.println("--------------------");
    }

}
