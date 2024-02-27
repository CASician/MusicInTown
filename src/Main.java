import main.BusinessLogic.AccessController;
import main.BusinessLogic.ProgramController;
import main.Interface.AccessInterface;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        ProgramController programController = new ProgramController();

        programController.run();
    }
}