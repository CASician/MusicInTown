package main.Interface;

import main.DomainModel.Musician;

public class MusicianInterface extends BasicUserInterface {

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("'s' to see event subscriptions, ");
        System.out.println("'f' to do an event subscription. ");
    }

    public void eventSubscription() {
        System.out.println("Enter the event ID: ");
    }

    //Function for testing
    public void printMusicianInfo(Musician musician) {
        System.out.println("Name: " + musician.name);
        System.out.println("Email: " + musician.getEmail());
        System.out.println("Genre: " + musician.genre);
        System.out.println("Component Number: " + musician.componentNumb);

    }


}
