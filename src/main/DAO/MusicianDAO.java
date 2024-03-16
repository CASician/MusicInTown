package main.DAO;

import main.DomainModel.Musician;

public class MusicianDAO {
    Musician musician;

    public MusicianDAO(String username) {
        //Access the database to retrieve the info of the musician and generate a new instance
        musician = new Musician("CoolBeans", "Pop", username, 1, 2);
    }

    public Musician getMusician() {
        return musician;
    }
}
