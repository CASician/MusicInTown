package main.Interface;

import main.DomainModel.Owner;
import main.DomainModel.PrivateEvent;
import main.DomainModel.UserType;

public class OwnerInterface extends BasicUserInterface {
    public void printOwnerInfo(Owner owner) {
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.OWNER);
        System.out.println("Name: " + owner.getName());
        System.out.println("Username: " + owner.getUsername());
        System.out.println("PLACE OWNED");
        System.out.println("Name: " + owner.getPlace().getName());
        System.out.println("City: " + owner.getPlace().getCity());
        System.out.println("Address: " + owner.getPlace().getAddress());
        System.out.println("--------------------");

    }

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
    }

    public void eventCreated(PrivateEvent event) {
        if(event != null) {
            System.out.println("The event was created with success, event info: ");
            System.out.print("EVENT NAME: " + event.getName());
            System.out.print(", DATE: " + event.getDate());
            System.out.print(", CITY: " + event.getPlace().getCity());
            System.out.print(", ADDRESS: " + event.getPlace().getAddress());
            System.out.println(", TYPE: " + event.getType());
            System.out.println("--------------------");
        }
        else {
            System.out.println("Event was not created");
        }
    }
}
