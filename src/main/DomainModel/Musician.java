package main.DomainModel;

import java.util.Date;

public class Musician extends BasicUser {

    public String name;
    public String genre;
    public int componentNumb;
    public Date fondation;

    public Musician(String name, String genre, String username, String email, int id, int numb,
                    String city, Date fondation) {
        super(id, email, username, city);
        this.name = name;
        this.genre = genre;
        this.componentNumb = numb;
        this.fondation = fondation;
    }

    public Date getFondation() {
        return fondation;
    }

    public int getComponentNumb() {
        return componentNumb;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

}
