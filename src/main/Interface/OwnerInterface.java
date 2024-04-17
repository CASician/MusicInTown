package main.Interface;

import main.DomainModel.Owner;
import main.DomainModel.PrivateEvent;
import main.DomainModel.UserType;

/*
* Class used to display specific views of the owner users
*/
public class OwnerInterface extends BasicUserInterface {
    public void printOwnerInfo(Owner owner) {
        //Prints all the info of owner user that is taken as a parameter
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.OWNER);
        System.out.println("Name: " + owner.getName());
        System.out.println("Username: " + owner.getUsername());
        System.out.println("PLACE OWNED");
        if(owner.getPlace() != null) {
            System.out.println("Name: " + owner.getPlace().getName());
            System.out.println("City: " + owner.getPlace().getCity());
            System.out.println("Address: " + owner.getPlace().getAddress());
        } else {
            System.out.println("No places linked to this owner");
        }
        System.out.println("--------------------");

    }

    public void eventsInterface() {
        //Prints the basic interface of the events menu and it adds the specific owner events view
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
        System.out.println("4 to accept proposed events, ");
    }

    public void eventCreated(PrivateEvent event) {
        //Prints all the info of the event created by the owner. The event is taken as a parameter
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
