package main.BusinessLogic;

import main.DAO.EventsToBeAcceptedDAO;
import main.DAO.MunicipalityDAO;
import main.DomainModel.*;
import main.Interface.MunicipalityInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Boolean.TRUE;

/*
* Class that controls all the actions of the Municipality.
*/
public class MunicipalityController extends BasicUserController{
    private final EventController eventController;
    //private final MunicipalityDAO municipalityDAO;
    private final Municipality municipality;
    UserChoices.MunicipalityActions municipalityActions;
    private final MunicipalityInterface municipalityInterface;

    public MunicipalityController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        this.municipality = MunicipalityDAO.getMunicipality(username);
        this.eventController = eventController;
        municipalityInterface = new MunicipalityInterface();
        municipalityActions = null;
    }

    public void municipalityFunctions() throws SQLException{
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
                    municipalityInterface.printPrivatePlaces(placesController.getPrivatePlaces());
                    municipalityInterface.printPublicPlaces(placesController.getPublicPlaces());
                    break;
                default:
                    break;
            }
        }
    }

    private void eventsManagement() throws SQLException {
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

    private void acceptEvent() throws SQLException {
        // Create object to be removed and the list that will be used
        PublicEvent toBeRemoved = null;
        ArrayList<PublicEvent> events = municipality.getEventsToBeAccepted();

        // Checks if the array is not empty
        if (!events.isEmpty()) {
            // print events to be accepted
            municipalityInterface.printPublicEvents(events);

            // take input as id of the event
            System.out.println("---------------");
            System.out.println("Select the ID of the event you want to accept: ");
            input = getInteger();

            // search for the requested event
            if (input >= 0) {
                for (PublicEvent event: events){
                    if (event.getId() == input){
                        toBeRemoved = event;
                        // set the accepted field to true
                        event.setAccepted(TRUE);
                        // remove event from table in database
                        EventsToBeAcceptedDAO.delete(event.getId());
                        // Show results
                        System.out.println("Event ACCEPTED!");
                    }
                }
                // remove event from array
                municipality.delete_event(toBeRemoved);
            } else { // Error message if the ID is not valid
                accessInterface.invalidChoice();
            }
        } else { // Here the array is empty
            System.out.print("No Events to be accepted. ");
        }
    }
}
