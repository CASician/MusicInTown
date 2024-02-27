package main.DAO;

import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Objects;

public class AccessDAO {

    public UserType login(HashMap<String, String> input) {
        UserType userType = null;
        if(Objects.equals(input.get("email"), "paolo@gmail.com") && Objects.equals(input.get("password"), "paba")) {
            userType = UserType.MUSICIAN; //Return the userType of the profile
        }
        return userType;
    }
}
