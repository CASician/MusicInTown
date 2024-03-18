package main.Interface;

import main.DomainModel.Planner;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;
import main.DomainModel.UserType;

public class PlannerInterface extends BasicUserInterface {
    public PlannerInterface() {

    }

    public void printPlannerInfo(Planner planner) {
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.PLANNER);
        System.out.println("Name: " + planner.getName());
        System.out.println("Username: " + planner.getUsername());
        System.out.println("ID: " + planner.getId());
        System.out.println("--------------------");
    }

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
    }

    public void createEvent() {
        System.out.println("Press 0 for a private event, 1 for a public event: ");
    }

    public void getId() { System.out.println("Insert the ID of the place: "); }

    public void privateEventCreated(PrivateEvent event) {
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
