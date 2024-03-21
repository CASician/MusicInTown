package main.Interface;

import main.DomainModel.User;
import main.DomainModel.UserType;

/*
 * Class used to display specific views of the basic users
 */
public class UserInterface extends BasicUserInterface {

    public void printUserInfo(User user) {
        //Print all the info of the user that is taken as a parameter
        System.out.println("--------------------");
        System.out.println("User Type: " + UserType.USER);
        System.out.println("Name: " + user.getName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Username: " + user.getUsername());
        System.out.println("--------------------");

    }
}
