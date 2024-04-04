package main.DAO;

import main.DomainModel.UserType;

import java.util.HashMap;
import java.util.Objects;

public class AccessDAO {

    public UserType login(HashMap<String, String> input) {
        UserType userType = null;

        //Database request (email, password)
        if(Objects.equals(input.get("username"), "paba") && Objects.equals(input.get("password"), "123")) {
            userType = UserType.MUSICIAN; //Return the userType of the profile
        }
        if(Objects.equals(input.get("username"), "miguelito") && Objects.equals(input.get("password"), "123")) {
            userType = UserType.USER; //Return the userType of the profile
        }
        if(Objects.equals(input.get("username"), "owner") && Objects.equals(input.get("password"), "123")) {
            userType = UserType.OWNER; //Return the userType of the profile
        }
        if(Objects.equals(input.get("username"), "steven") && Objects.equals(input.get("password"), "123")){
            userType = UserType.PLANNER;
        }
        if(Objects.equals(input.get("username"), "florence") && Objects.equals(input.get("password"), "123")) {
            userType = UserType.MUNICIPALITY;
        }
        return userType;
    }
}
