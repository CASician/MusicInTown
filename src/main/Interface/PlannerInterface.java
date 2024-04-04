package main.Interface;

import main.DomainModel.Planner;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;
import main.DomainModel.UserType;

/*
* Class used to display specific views of the planner users
*/
public class PlannerInterface extends BasicUserInterface {

    public void printPlannerInfo(Planner planner) {
        //Prints all the info of the planner user that is taken as a parameter
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.PLANNER);
        System.out.println("Name: " + planner.getName());
        System.out.println("Username: " + planner.getUsername());
        System.out.println("ID: " + planner.getId());
        System.out.println("--------------------");
    }

    public void eventsInterface() {
        //Prints the basic interface of the events menu and it adds the specific planner events view
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
    }

    public void createEvent() {
        System.out.println("Press 0 for a private event, 1 for a public event: ");
    }

    public void getId() { System.out.println("Insert the ID of the place: "); }

    public void privateEventCreated(PrivateEvent event) {
        //Prints all the info of the private event created by the planner. The event is taken as a parameter
        if(event != null) {
            System.out.println("The event was created and now is waiting for approval from the owner, event info: ");
            System.out.print("EVENT NAME: " + event.getName());
            System.out.print(", DATE: " + event.getDate());
            System.out.print(", CLUB NAME: " + event.getPlace().getName());
            System.out.print(", CITY: " + event.getPlace().getCity());
            System.out.println(", TYPE: " + event.getType());
            System.out.println(", ADDRESS: " + event.getPlace().getAddress());
            System.out.println("--------------------");
        }
        else {
            System.out.println("Event was not created");
        }
    }

    public void publicEventCreated(PublicEvent event) {
        //Prints all the info of the public event created by the owner. The event is taken as a parameter
        if(event != null) {
            System.out.println("The event was created and now is waiting for approval from municipality, event info: ");
            System.out.print("EVENT NAME: " + event.getName());
            System.out.print(", DATE: " + event.getDate());
            System.out.print(", CITY: " + event.getPlace().getCity());
            System.out.println(", TYPE: " + event.getType());
            System.out.println("--------------------");
        }
        else {
            System.out.println("Event was not created");
        }
    }

}
