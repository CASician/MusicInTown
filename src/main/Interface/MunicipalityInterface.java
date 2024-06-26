package main.Interface;

import main.DomainModel.Municipality;
import main.DomainModel.UserType;

import java.util.Scanner;

/*
* Class used to display specific views of the municipality users
*/
public class MunicipalityInterface extends BasicUserInterface {

    public void printMunicipalityInfo(Municipality municipality) {
        //Prints the info of the municipality user that is taken as a parameter
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.MUNICIPALITY);
        System.out.println("Username: " + municipality.getUsername());
        System.out.println("City: " + municipality.getCity());
        System.out.println("Id: " + municipality.getId());
        System.out.println("--------------------");
    }

    public void eventsInterface() {
        //Prints the basic interface of the events menu and it adds the specific municipality events view
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
        System.out.println("4 to accept proposed events, ");
    }
}
