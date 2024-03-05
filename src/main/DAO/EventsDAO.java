package main.DAO;


import main.DomainModel.Event;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    PlannerDAO plannerDAO;
    int lastId;
    PlacesDAO placesDAO;
    List<PublicEvent> publicEvents;
    List<PrivateEvent> privateEvents;

    public EventsDAO() {
        publicEvents = new ArrayList<>();
        privateEvents = new ArrayList<>();
        plannerDAO = new PlannerDAO();
        placesDAO = new PlacesDAO();
        //Set lastId to the latest id gave to an event doing a query to the database.
        lastId = 0;

        publicEvents.add(new PublicEvent("Festival Jazz Fusion", true, LocalDate.of(2024, 5, 25), plannerDAO.planners[0],
                placesDAO.publicPlaces[0], "3 giorni", "firenze","Jazz Fusion"));
        lastId += 1;
        publicEvents.get(0).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Rock Indie", true, LocalDate.of(2025, 1, 20), plannerDAO.planners[1],
                placesDAO.publicPlaces[1], "1 giorno", "milano","Rock Indie"));
        lastId += 1;
        publicEvents.get(1).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Classico Contemporaneo", false, LocalDate.of(2024, 10, 20), plannerDAO.planners[1],
                placesDAO.publicPlaces[2], "1 giorno", "bologna","Classico Contemporaneo"));
        lastId += 1;
        publicEvents.get(2).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Indie Acustico", false, LocalDate.of(2024, 8, 2), plannerDAO.planners[2],
                placesDAO.publicPlaces[2], "2 giorni", "firenze","Indie Acustico"));
        lastId += 1;
        publicEvents.get(3).setId(lastId);

    }



    public List<PublicEvent> getPublicEvents() {
        return publicEvents;
    }

    public List<PrivateEvent> getPrivateEvents() {
        return privateEvents;
    }

    //TODO: Aggiustare questa funzione

    /*
    public ArrayList<String[]> getEventsSubscriptions(int id) {
        ArrayList<String[]> filteredEvents = null;

        for (String[] event : events) {
            if (Objects.equals(event[1], String.valueOf(id))) {
                filteredEvents.add(event);
            }
        }

        return filteredEvents;
    }

    */
}