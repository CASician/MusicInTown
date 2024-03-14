package main.Interface;
import java.util.Scanner;

public class AccessInterface extends BasicUserInterface {
    Scanner scanner;
    public AccessInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void firstView() {
        System.out.println("Choose an option: ");
        System.out.println("0. login ");
        System.out.println("1. exit");
    }

    public void askEmail() {
        System.out.println("Email: ");
    }

    public void askPassword() {
        System.out.println("Password: ");
    }

    public void success(String username) {
        System.out.println("Login was successful, welcome " + username);
    }

    public void loginFailure() {
        System.out.println("Wrong Email or Password");
    }

    public void tryAgain() {
        System.out.println("Choose an option: ");
        System.out.println("0. try again");
        System.out.println("1. exit");
    }

}
