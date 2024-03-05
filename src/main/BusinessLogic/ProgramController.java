package main.BusinessLogic;

import main.DomainModel.Musician;
import main.DomainModel.UserType;

import java.util.Objects;

public class ProgramController extends InputController {
    AccessController accessController;
    MusicianController musicianController;
    PlannerController plannerController;
    MunicipalityController municipalityController;
    UserController userController;
    OwnerController ownerController;
    UserType userType;
    //String userType = null;

    public ProgramController() {
        System.out.println("qui");
        this.accessController = new AccessController();
    }

    public void run() {

        //Ask user to login or to exit the program
        UserActions input = getFirstInput();
        if(input != null) {
            switch (input) {
                case EXIT:
                    break;
                case LOGIN:
                    userType = accessController.login();
            }
        }

        //continue execution depending on the type of user
        if(userType != null) {
            switch (userType) {
                case MUSICIAN:
                    this.musicianController = new MusicianController(accessController.email);
                    this.musicianController.musicianFunctions();
                    break;
                case PLANNER:
                    this.plannerController = new PlannerController(accessController.email);
                    break;
                case MUNICIPALITY:
                    this.municipalityController = new MunicipalityController(accessController.email);
                    break;
                case USER:
                    this.userController = new UserController(accessController.email);
                    break;
                case OWNER:
                    this.ownerController = new OwnerController(accessController.email);

            }
        }
    }


}
