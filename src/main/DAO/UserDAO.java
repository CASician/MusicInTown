package main.DAO;

import main.DomainModel.User;

public class UserDAO {

    User user;

    public UserDAO(String user) {
        //Access the database to retrieve the info of the musician and generate a new instance
        this.user = new User(11, "miguelito", "miguelito@gmail.com", "lollone", "Firenze");
    }

    public User getUser() {
        return user;
    }

}
