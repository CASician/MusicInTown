package main.BusinessLogic;

import main.DAO.MunicipalityDAO;
import main.DomainModel.Municipality;
import main.Interface.MunicipalityInterface;

import java.time.LocalDate;
import java.util.Objects;

public class MunicipalityController extends BasicUserController {
    EventController eventController;
    MunicipalityDAO municipalityDAO;
    Municipality municipality;
    UserChoices.MunicipalityActions municipalityActions;
    MunicipalityInterface municipalityInterface;

    public MunicipalityController(String municipality, EventController eventController, PlacesController placesController) {
        super(placesController);
        municipalityDAO = new MunicipalityDAO();
        this.municipality = municipalityDAO.getMunicipality(municipality);
        this.eventController = eventController;
        municipalityInterface = new MunicipalityInterface();
        municipalityActions = null;
    }

    public void musicianFunctions() {
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
                case CreateEvent:
                    if(privatePublicEvent() == 0) {
                        //createPrivateEvent();
                    }
                    else {
                        //createPublicEvent();
                    }
                    break;
                case AcceptEvents:
                    break;
                case Exit:
                    quitMenu = true;
                    break;
            }
        }
    }

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
