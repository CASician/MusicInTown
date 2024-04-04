package main.BusinessLogic;

import main.DAO.MunicipalityDAO;
import main.DomainModel.Municipality;
import main.DomainModel.PublicEvent;
import main.DomainModel.PublicPlace;
import main.Interface.MunicipalityInterface;

import java.time.LocalDate;
import java.util.Objects;

/*
* Class that controls all the actions of the Municipality.
*/
public class MunicipalityController extends BasicUserController {
    private final EventController eventController;
    private final MunicipalityDAO municipalityDAO;
    private final Municipality municipality;
    UserChoices.MunicipalityActions municipalityActions;
    private final MunicipalityInterface municipalityInterface;

    public MunicipalityController(String municipality, EventController eventController, PlacesController placesController) {
        super(placesController);
        municipalityDAO = new MunicipalityDAO();
        this.municipality = municipalityDAO.getMunicipality(municipality);
        this.eventController = eventController;
        municipalityInterface = new MunicipalityInterface();
        municipalityActions = null;
    }

    public void municipalityFunctions() {
        /*
        * Wrapper that select and call the right function to execute, based on the user input on the first menu.
        * The first menu is the one about showing user and places info, quitting the app or entering the events menu
        */
        while(!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            municipalityInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            switch (basicUserOptions) {
                case SeeInfo:
                    municipalityInterface.printMunicipalityInfo(municipality);
                    break;
                case SeeEventsMenu:
                    eventsManagement();
                    break;
                case Exit:
                    municipalityInterface.logOut();
                    break;
                case SeeAllPlaces:
                    municipalityInterface.printPrivatePlaces(placesController.getPrivatePlacesList());
                    municipalityInterface.printPublicPlaces(placesController.getPublicPlacesList());
                    break;
                default:
                    break;
            }
        }
    }

    private void eventsManagement() {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the second menu.
         * The second menu is the one about managing and showing all the events.
        */
        boolean quitMenu = false;
        while (!quitMenu) {
            municipalityInterface.eventsInterface();
            municipalityActions = getMunicipalityInput();
            switch (municipalityActions) {
                case SeeAllEvents:
                    municipalityInterface.printPrivateEvents(eventController.getPrivateEvents());
                    municipalityInterface.printPublicEvents(eventController.getPublicEvents());
                    break;
                case FilterEvents:
                    LocalDate date = getDateFilter();
                    municipalityInterface.printPrivateEvents(eventController.getPrivateEventsFiltered(date));
                    municipalityInterface.printPublicEvents(eventController.getPublicEventsFiltered(date));
                    break;
                case AcceptEvents:
                    acceptEvent();
                    break;
                case Exit:
                    quitMenu = true;
                    break;
            }
        }
    }

    private boolean acceptEvent() {
        return false;
    };

    private UserChoices.MunicipalityActions getMunicipalityInput() {
        municipalityActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.MunicipalityActions.values().length) {
            municipalityActions = UserChoices.MunicipalityActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            municipalityActions = null;
        }
        input = 0;
        return municipalityActions;
    }
}
