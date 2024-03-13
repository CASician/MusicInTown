package main.DAO;

import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Objects;

public class AccessDAO {

    public UserType login(HashMap<String, String> input) {
        UserType userType = null;

        //Database request (email, password)
        if(Objects.equals(input.get("email"), "paolo@gmail.com") && Objects.equals(input.get("password"), "paba")) {
            userType = UserType.MUSICIAN; //Return the userType of the profile
        }
        if(Objects.equals(input.get("email"), "miguelito@gmail.com") && Objects.equals(input.get("password"), "123")) {
            userType = UserType.USER; //Return the userType of the profile
        }
        return userType;
    }
}
