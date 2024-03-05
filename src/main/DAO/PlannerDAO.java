package main.DAO;

import main.DomainModel.Planner;

public class PlannerDAO {
    Planner[] planners;

    public PlannerDAO() {
        planners = new Planner[3];

        planners[0] = new Planner(1, "briatore@gmail.com", "flavio", "milano");
        planners[1] = new Planner(2, "basalari@gmail.com", "steven", "milano");
        planners[2] = new Planner(3, "bryan@gmail.com", "bryanino", "firenze");
    }

}
