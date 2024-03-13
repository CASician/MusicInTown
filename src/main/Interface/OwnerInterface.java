package main.Interface;

import main.DomainModel.Owner;

public class OwnerInterface extends BasicUserInterface {
    public void printOwnerInfo(Owner owner) {
        System.out.println("--------------------");
        System.out.println("Name: " + owner.getName());
        System.out.println("Email: " + owner.getEmail());
        System.out.println("City: " + owner.getCity());
        System.out.println("PLACE OWNED");
        System.out.println("Name: " + owner.getPlace().getName());
        System.out.println("City: " + owner.getPlace().getCity());
        System.out.println("Address: " + owner.getPlace().getAddress());
        System.out.println("--------------------");

    }

    public void eventsManagement() {
    }
}
