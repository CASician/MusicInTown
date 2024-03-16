package main.Interface;

import main.DomainModel.User;
import main.DomainModel.UserType;

public class UserInterface extends BasicUserInterface {

    public void printUserInfo(User user) {
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.USER);
        System.out.println("Name: " + user.getName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Username: " + user.getUsername());
        System.out.println("--------------------");

    }
}
