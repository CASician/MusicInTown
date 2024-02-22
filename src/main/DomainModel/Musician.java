package main.DomainModel;

import java.util.Date;

public class Musician extends BasicUser {

    public String name;
    public String genre;
    public int componentNumb;
    public Date foundation;

    public Musician(String name, String genre, String username, String email, int id, int numb,
                    String city, Date foundation) {
        super(id, email, username, city);
        this.name = name;
        this.genre = genre;
        this.componentNumb = numb;
        this.foundation = foundation;
    }

    public Date getFoundation() {
        return foundation;
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
