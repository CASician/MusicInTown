package main.BusinessLogic;

import main.DAO.MusicianDAO;
import main.DAO.PrivateEventDAO;
import main.DAO.PublicEventDAO;
import main.DomainModel.Event;
import main.DomainModel.Musician;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;
import main.Interface.MusicianInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/*
* Class that controls all the actions of the Musician.
*/
public class MusicianController extends BasicUserController {
    private final Musician musician;
    private final MusicianInterface musicianInterface;
    UserChoices.MusicianActions musicianActions;
    private final EventController eventController;

    public MusicianController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        this.musician = MusicianDAO.getMusician(username);
        musicianInterface = new MusicianInterface();
        musicianActions = null;
        this.eventController = eventController;
    }

    public void musicianFunctions() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the first menu.
         * The first menu is the one about showing user and places info, quitting the app or entering the events menu
        */
        while(!Objects.equals(basicUserOptions, UserChoices.BasicUser.Exit)) {
            musicianInterface.basicInterface();
            basicUserOptions = firstMenuInput();
            switch (basicUserOptions) {
                case SeeInfo:
                    musicianInterface.printMusicianInfo(musician);
                    break;
                case SeeEventsMenu:
                    eventsManagement();
                    break;
                case Exit:
                    musicianInterface.logOut();
                    break;
                case SeeAllPlaces:
                    musicianInterface.printPrivatePlaces(placesController.getPrivatePlaces());
                    musicianInterface.printPublicPlaces(placesController.getPublicPlaces());
                    break;
                default:
                    break;
            }
        }
    }

    public void eventsManagement() throws SQLException {
        /*
         * Wrapper that select and call the right function to execute, based on the user input on the second menu.
         * The second menu is the one about managing and showing all the events.
        */
        boolean quitMenu = false;
        while(!quitMenu) {
            musicianInterface.eventsInterface();
            musicianActions = getMusicianInput();
            switch (musicianActions) {
                case SeeAllEvents:
                    musicianInterface.printPrivateEvents(eventController.getPrivateEvents());
                    musicianInterface.printPublicEvents(eventController.getPublicEvents());
                    break;
                case SeeEventsSubscriptions:
                    musicianInterface.getSubscriptions(musician.getPublicEvents(), musician.getPrivateEvents());
                    break;
                case SubscribeEvent:
                    subscribeToEvent();
                    break;
                case FilterEvents:
                    LocalDate date = getDateFilter();
                    musicianInterface.printPublicEvents(eventController.getPublicEventsFiltered(date));
                    musicianInterface.printPrivateEvents(eventController.getPrivateEventsFiltered(date));
                    break;
                case Exit:
                    quitMenu = true;
                    break;
            }
        }
    }

    public UserChoices.MusicianActions getMusicianInput() {
        //Get the inputs from the musician and returns the specific action set inside the UserChoices Enumeration
        musicianActions = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.MusicianActions.values().length) {
            musicianActions = UserChoices.MusicianActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            musicianActions = null;
        }
        input = 0;
        return musicianActions;
    }

    public void subscribeToEvent() throws SQLException {
        // I already have my musician, I need to get the event
        // Get the events
        ArrayList<PublicEvent> publicEvents = PublicEventDAO.getAll();
        ArrayList<PrivateEvent> privateEvents = PrivateEventDAO.getAll();
        
        publicEvents = openPublicEvents(publicEvents);
        privateEvents = openPrivateEvents(privateEvents);


        // ask ID of the event
        System.out.println("---------------");
        System.out.println("Select the ID of the event you want to Subscribe to: ");
        input = getInteger();

        // find Event with such ID
        boolean found = false;
        // Look in Public Events
        if(input >= 0){
            for(Event event_itr: publicEvents){
                if(event_itr.getId() == input){
                    found = true;
                    // Add it to DB and to the respective array in Musician and Event.
                    eventController.subscribeEvent(musician, event_itr);
                    break;
                }
            }
        }
        // Look in Private Events
        if(!found){
            for(Event event_itr: privateEvents){
                if(event_itr.getId() == input){
                    // Add it to DB and to the respective array in Musician and Event.
                    eventController.subscribeEvent(musician, event_itr);
                    break;
                }
            }
        }
    }

    private ArrayList<PrivateEvent> openPrivateEvents(ArrayList<PrivateEvent> privateEvents) {
    }

    private ArrayList<PublicEvent> openPublicEvents(ArrayList<PublicEvent> publicEvents) {
    }
}
