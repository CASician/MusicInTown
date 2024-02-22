import main.BusinessLogic.AccessController;
import main.BusinessLogic.ProgramController;
import main.Interface.AccessInterface;

import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        ProgramController programController = new ProgramController();

        programController.start();
    }
}