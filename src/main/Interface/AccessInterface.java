package main.Interface;

import java.util.Objects;
import java.util.Scanner;

public class AccessInterface {
    Scanner scanner;
    public AccessInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void firstView() {
        System.out.println("Press 'l' to logIn or 'e' to exit: ");
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

    public void failure() {
        System.out.println("Wrong Email or Password");
    }

}
