package main.Interface;

import main.DomainModel.User;

public class UserInterface extends BasicUserInterface {

    public void printUserInfo(User user) {
        System.out.println("--------------------");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("--------------------");

    }
}
