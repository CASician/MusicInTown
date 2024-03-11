package main.Interface;

import main.DomainModel.User;

public class UserInterface extends BasicUserInterface {

    public void printUserInfo(User user) {
        System.out.println("--------------------");
        System.out.println("Name: " + user.name);
        System.out.println("Email: " + user.getEmail());
        System.out.println("City: " + user.getCity());
        System.out.println("--------------------");

    }
}
