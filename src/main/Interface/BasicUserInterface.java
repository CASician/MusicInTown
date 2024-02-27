package main.Interface;

public abstract class BasicUserInterface {

    public void basicInterface() {
        System.out.println("Press 'e' to exit, ");
        System.out.println("'v' to enter events menu, ");
        System.out.println("'i' to enter user info menu, ");
    }

    public void basicEventsInterface() {
        System.out.println("Press 'a' to visualize all events, " );
        System.out.println("'f' to filter events, " );
        System.out.println("'e' to go back, ");
    }

    public void filterDate() {
        System.out.println("Insert further date 'gg-mm-yyy': ");
    }

    public void filterPlace() {
        System.out.println("Insert municipality name: ");
    }

}
