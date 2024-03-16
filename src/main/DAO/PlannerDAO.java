package main.DAO;

import main.DomainModel.Planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlannerDAO {
    List<Planner> planners;

    public PlannerDAO() {
        planners = new ArrayList<>();

        planners.add( new Planner(1, "briatore@gmail.com", "flavio"));
        planners.add( new Planner(2, "basalari@gmail.com", "steven"));
        planners.add( new Planner(3, "bryan@gmail.com", "bryanino"));
    }

    public Planner getPlanner(String planner) {
        Planner planner1 = null;
        for (Planner value : planners) {
            if (Objects.equals(value.getUsername(), planner)) {
                planner1 = value;
                break;
            }
        }
        return planner1;
    }
}
