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
        System.out.println("4 to select a musician for an event");
    }

    public void createEvent() {
        System.out.println("Press 0 for a private event, 1 for a public event: ");
    }

    public void getName() { System.out.println("Insert the Name of the place: "); }

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
            System.out.println("--------------------");
        }
    }

    public void noEventsCreated() {
        System.out.println("There are no open events created");
        System.out.println("--------------------");
    }

    public void chooseMusician(boolean isMusician) {
        if(isMusician) {
            System.out.println("Select the ID of the musician you want to pick for the event:");
            System.out.println("--------------------");
        }
        else {
            System.out.println("There are no musician subscribed to the event");
            System.out.println("--------------------");
        }
    }

    public void successChoose() {
        System.out.println("Musician selected successfully");
        System.out.println("--------------------");
    }
}
