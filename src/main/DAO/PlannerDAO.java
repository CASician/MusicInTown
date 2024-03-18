package main.DAO;

import main.DomainModel.Planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlannerDAO {
    List<Planner> planners;

    public PlannerDAO() {
        planners = new ArrayList<>();

        planners.add( new Planner(1, "briatore", "Flavio"));
        planners.add( new Planner(2, "basalari", "Steven"));
        planners.add( new Planner(3,"bryanino", "Bryan"));
    }

    public Planner getPlanner(String planner) {
        Planner planner1 = null;
        for (Planner value : planners) {
            if (Objects.equals(value.getUsername().toLowerCase(), planner.toLowerCase())) {
                planner1 = value;
                break;
            }
        }
        return planner1;
    }
}
