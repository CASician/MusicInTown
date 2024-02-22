package main.DAO;

import java.util.HashMap;
import java.util.Objects;

public class AccessDAO {

    public String login(HashMap<String, String> input) {
        if(Objects.equals(input.get("email"), "paolo@gmail.com") && Objects.equals(input.get("password"), "paba")) {
            String musician = null;
            musician = "m";
            return musician;
        }
        else return null;
    }
}
