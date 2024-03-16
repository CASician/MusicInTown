package main.DAO;

import main.DomainModel.Planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlannerDAO {
    List<Planner> planners;

    public PlannerDAO() {
        planners = new ArrayList<>();

        planners.add( new Planner(1, "Flavio", "briatore"));
        planners.add( new Planner(2, "Steven", "basalari"));
        planners.add( new Planner(3, "Bryan","bryanino"));
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
