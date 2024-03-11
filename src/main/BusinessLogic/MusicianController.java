package main.BusinessLogic;

import main.DAO.MusicianDAO;
import main.DomainModel.Musician;
import main.Interface.MusicianInterface;

import java.time.LocalDate;
import java.util.Objects;

public class MusicianController extends InputController {
    MusicianDAO musicianDAO;
    Musician musician;
    MusicianInterface musicianInterface;
    UserChoices.MusicianActions operation;
    EventController eventController;

    public MusicianController(String musician) {
        eventController = new EventController();
        musicianDAO = new MusicianDAO(musician);
        this.musician = musicianDAO.getMusician();
        musicianInterface = new MusicianInterface();
        operation = null;
        eventController = new EventController();
    }

    public void musicianFunctions() {
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
                default:
                    break;
            }
        }
    }

    public void eventsManagement() {
        boolean quitMenu = false;
        while(!quitMenu) {
            musicianInterface.eventsInterface();
            operation = getMusicianInput();
            switch (operation) {
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
                                eventController.subscribePrivateEvent(musician.name, musician.getId(), getId()));
                        musicianInterface.privateSubscriptionDone(
                                musician.getPrivateEvents().get(musician.getPrivateEvents().size()-1));
                    }
                    else {
                        musician.addPublicSubscription(
                                eventController.subscribePublicEvent(musician.name, musician.getId(), getId()));
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
        operation = null;
        input = getInteger();
        if (input >= 0 && input < UserChoices.MusicianActions.values().length) {
            operation = UserChoices.MusicianActions.values()[input];
        } else {
            accessInterface.invalidChoice();
            operation = null;
        }
        input = 0;
        return operation;
    }

}
