package main.BusinessLogic;

import main.DomainModel.UserType;

/*
* Class that controls the entire flow of the application.
* The method run() calls the functions to allow the access.
* After this it generates the correct user and call all the related functions.
*/

public class ProgramController extends BasicUserController {
    private final AccessController accessController;
    MusicianController musicianController;
    PlannerController plannerController;
    MunicipalityController municipalityController;
    UserController userController;
    OwnerController ownerController;
    private final PlacesController placesController;
    private final EventController eventController;
    UserType userType;
    //String userType = null;

    public ProgramController() {
        super(new PlacesController());
        placesController = new PlacesController();
        eventController = new EventController(placesController);
        this.accessController = new AccessController();
    }

    public void run() {
        //Control and execute all the application flow. It's called from the main.

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

        //Continue execution depending on the type of user
        if(userType != null) {
            switch (userType) {
                case MUSICIAN:
                    this.musicianController = new MusicianController(accessController.username, eventController, placesController);
                    this.musicianController.musicianFunctions();
                    break;
                case PLANNER:
                    this.plannerController = new PlannerController(accessController.username, eventController, placesController);
                    this.plannerController.plannerFunctions();
                    break;
                case MUNICIPALITY:
                    this.municipalityController = new MunicipalityController(accessController.username, eventController, placesController);
                    this.municipalityController.municipalityFunctions();
                    break;
                case USER:
                    this.userController = new UserController(accessController.username, eventController, placesController);
                    this.userController.userFunctions();
                    break;
                case OWNER:
                    this.ownerController = new OwnerController(accessController.username, eventController, placesController);
                    this.ownerController.ownerFunctions();

            }
        }
    }
}
