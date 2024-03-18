package main.Interface;

import main.DomainModel.Municipality;
import main.DomainModel.UserType;

import java.util.Scanner;

public class MunicipalityInterface extends BasicUserInterface {

    Scanner scanner;
    public MunicipalityInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void printMunicipalityInfo(Municipality municipality) {
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.MUNICIPALITY);
        System.out.println("Username: " + municipality.getUsername());
        System.out.println("City: " + municipality.getCity());
        System.out.println("Id: " + municipality.getId());
        System.out.println("--------------------");
    }

    public void eventsInterface() {
        basicEventsInterface();
        System.out.println("3 to create a new event, ");
    }
}
