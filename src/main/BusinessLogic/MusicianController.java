package main.BusinessLogic;

import main.DAO.MusicianDAO;
import main.DomainModel.Musician;
import main.Interface.MusicianInterface;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MusicianController {
    MusicianDAO musicianDAO;
    Musician musician;
    MusicianInterface musicianInterface;
    String operation;
    Scanner scanner;
    EventController eventController;

    public MusicianController(String musician) {
        //this.musician = new Musician(musician);
        this.musicianDAO = new MusicianDAO(musician);
        this.musician = musicianDAO.getMusician();
        this.musicianInterface = new MusicianInterface();
        this.operation = null;
        this.scanner = new Scanner(System.in);
        this.eventController = new EventController();
    }

    public void musicianFunctions() {
        musicianInterface.printMusicianInfo(musician);
        while(Objects.equals(operation, "e")) {
            this.musicianInterface.basicInterface();
            this.operation = this.scanner.nextLine();
            switch (operation) {
                case "v":
                    eventsManagement();
                case "i":
                    infoManagement();
                default:
                    break;
            }
        }
    }

    public void eventsManagement() {
        String input = null;
        musicianInterface.eventsInterface();

        eventController.getEventSubscriptions();
    }

    public void infoManagement() {

    }

}
