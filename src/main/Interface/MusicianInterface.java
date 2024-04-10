package main.Interface;

import main.DomainModel.Musician;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;
import main.DomainModel.UserType;

import java.util.ArrayList;
import java.util.List;

/*
* Class used to display specific views of the musician users
*/
public class MusicianInterface extends BasicUserInterface {

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("3 to see event subscriptions, ");
        System.out.println("4 to do an event subscription. ");
    }

    //Function for testing
    public void printMusicianInfo(Musician musician) {
        //Prints the info of the musician user that is taken as a parameter
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.MUSICIAN);
        System.out.println("Name: " + musician.getName());
        System.out.println("Username: " + musician.getUsername());
        System.out.println("Genre: " + musician.getGenre());
        System.out.println("Component Number: " + musician.getComponentNumb());
        System.out.println("Username: " + musician.getUsername());
        System.out.println("--------------------");

    }

    public void publicSubscriptionDone(PublicEvent event) {
        //Prints the info of the specific public event that the musician subscribed to
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
        //Prints the info of the specific private event that the musician subscribed to
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

    public void getSubscriptions(ArrayList<PublicEvent> publicEvents, ArrayList<PrivateEvent> privateEvents) {
        ////Prints the info of all the public and private events that the musician subscribed to
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
