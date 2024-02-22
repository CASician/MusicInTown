package main.BusinessLogic;

import main.DomainModel.Musician;

import java.util.Objects;

public class ProgramController {
    AccessController accessController;
    MusicianController musicianController;

    String userType = null;

    public ProgramController() {
        this.accessController = new AccessController();
    }

    public void start() {
        String input = accessController.start();
        switch (input) {
            case "e":
                break;
            case "l":
                userType = accessController.login();
        }
        if(userType != null) {
            //continue execution depending on the type of user
            switch (userType) {
                case "m":
                    this.musicianController = new MusicianController(accessController.email);
                case "u":

            }
        }
    }


}
