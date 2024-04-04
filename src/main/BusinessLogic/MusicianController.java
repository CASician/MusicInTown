package main.BusinessLogic;

import main.DAO.MusicianDAO;
import main.DomainModel.Musician;
import main.Interface.MusicianInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/*
* Class that controls all the actions of the Musician.
*/
public class MusicianController extends BasicUserController {
    private final MusicianDAO musicianDAO;
    private final Musician musician;
    private final MusicianInterface musicianInterface;
    UserChoices.MusicianActions musicianActions;
    private final EventController eventController;

    public MusicianController(String username, EventController eventController, PlacesController placesController) throws SQLException {
        super(placesController);
        musicianDAO = new MusicianDAO();
        this.musician = musicianDAO.getMusician(username);
        musicianInterface = new MusicianInterface();
        musicianActions = null;
        this.eventController = eventController;
    }

    public void musicianFunctions() {
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
                    musicianInterface.printPrivatePlaces(placesController.getPrivatePlacesList());
                    musicianInterface.printPublicPlaces(placesController.getPublicPlacesList());
                    break;
                default:
                    break;
            }
        }
    }

    public void eventsManagement() {
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
                    if(getEventType() == 0) {
                        musician.addPrivateSubscription(
                                eventController.subscribePrivateEvent(musician.getName(), musician.getId(), getId()));
                        musicianInterface.privateSubscriptionDone(
                                musician.getPrivateEvents().get(musician.getPrivateEvents().size()-1));
                    }
                    else {
                        musician.addPublicSubscription(
                                eventController.subscribePublicEvent(musician.getName(), musician.getId(), getId()));
                        musicianInterface.publicSubscriptionDone(
                                musician.getPublicEvents().get(musician.getPublicEvents().size()-1));
                    }
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
}
