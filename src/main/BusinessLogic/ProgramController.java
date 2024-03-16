package main.BusinessLogic;

import main.DomainModel.UserType;

public class ProgramController extends InputController {
    AccessController accessController;
    MusicianController musicianController;
    PlannerController plannerController;
    MunicipalityController municipalityController;
    UserController userController;
    OwnerController ownerController;
    PlacesController placesController;
    EventController eventController;
    UserType userType;
    //String userType = null;

    public ProgramController() {
        placesController = new PlacesController();
        eventController = new EventController(placesController);
        this.accessController = new AccessController();
    }

    public void run() {

        //Ask user to login or to exit the program
        UserChoices input = getFirstInput();
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
                    this.musicianController = new MusicianController(accessController.username, eventController);
                    this.musicianController.musicianFunctions();
                    break;
                case PLANNER:
                    this.plannerController = new PlannerController(accessController.username, eventController, placesController);
                    this.plannerController.plannerFunctions();
                    break;
                case MUNICIPALITY:
                    this.municipalityController = new MunicipalityController(accessController.username);
                    break;
                case USER:
                    this.userController = new UserController(accessController.username, eventController);
                    this.userController.userFunctions();
                    break;
                case OWNER:
                    this.ownerController = new OwnerController(accessController.username, eventController, placesController);
                    this.ownerController.ownerFunctions();

            }
        }
    }


}
