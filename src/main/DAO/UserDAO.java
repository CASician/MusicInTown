package main.DAO;

import main.DomainModel.User;

public class UserDAO {

    User user;

    public UserDAO(String user) {
        this.user = new User(1, "user", "generic", "city");
    }

    public User getUser() {
        return user;
    }

}
