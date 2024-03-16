package main.DAO;

import main.DomainModel.Musician;

public class MusicianDAO {
    Musician musician;

    public MusicianDAO(String email) {
        //Access the database to retrieve the info of the musician and generate a new instance
        musician = new Musician("CoolBeans", "Pop", "paolo", email, 1, 2);
    }

    public Musician getMusician() {
        return musician;
    }
}
