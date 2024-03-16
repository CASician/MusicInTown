package main.DAO;

import main.DomainModel.Planner;

public class PlannerDAO {
    Planner[] planners;

    public PlannerDAO() {
        planners = new Planner[3];

        planners[0] = new Planner(1, "briatore@gmail.com", "flavio");
        planners[1] = new Planner(2, "basalari@gmail.com", "steven");
        planners[2] = new Planner(3, "bryan@gmail.com", "bryanino");
    }

}
