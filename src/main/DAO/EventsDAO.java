package main.DAO;

import java.sql.*;

import main.BusinessLogic.PlacesController;
import main.DomainModel.Event;
import main.DomainModel.PrivateEvent;
import main.DomainModel.PublicEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    //DAO
    public void add(Event event) throws SQLException {
      Connection conn = DriverManager.getConnection("jdbc:postgresql:"+"MusicInTown");
      PreparedStatement insertEvent = conn.prepareStatement("INSERT INTO Events(id, name, open, date, city, type, duration) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
      insertEvent.setString(1, event.getName());
      insertEvent.setBoolean(2, event.isOpen());
      //quel value of lo uso perché event usa LocalDate e non Date
      insertEvent.setDate(3, Date.valueOf(event.getDate()));
      insertEvent.setString(4, event.getCity());
      insertEvent.setString(5, event.getType());
      insertEvent.setString(6, event.getDuration());
      insertEvent.executeUpdate();
      insertEvent.close();
      conn.close();
    };

    //TODO: implementare le altre DAO: update, delete, get(id), getAll



    // ROBA PABA ----------------------------------------------------------------------------------
    PlannerDAO plannerDAO;
    int lastId;
    PlacesController placesController;
    List<PublicEvent> publicEvents;
    List<PrivateEvent> privateEvents;

    public EventsDAO(PlacesController placesController) {
        //Retrieve and generate all the events from the database
        publicEvents = new ArrayList<>();
        privateEvents = new ArrayList<>();
        plannerDAO = new PlannerDAO();
        this.placesController = placesController;
        //Set lastId to the latest id gave to an event doing a query to the database.
        lastId = 0;
        int privatePlacesLength = placesController.getPrivatePlaces().size();
        int publicPlacesLength = placesController.getPublicPlaces().size();

        publicEvents.add(new PublicEvent("Festival Jazz Fusion", true, LocalDate.of(2024, 5, 25), plannerDAO.planners.get(0),
                placesController.getPublicPlaces().get(0), "3 giorni", "firenze","Jazz Fusion"));
        lastId += 1;
        publicEvents.get(0).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Rock Indie", true, LocalDate.of(2025, 1, 20), plannerDAO.planners.get(1),
                placesController.getPublicPlaces().get(1), "1 giorno", "milano","Rock Indie"));
        lastId += 1;
        publicEvents.get(1).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Classico Contemporaneo", false, LocalDate.of(2024, 10, 20), plannerDAO.planners.get(2),
                placesController.getPublicPlaces().get(2), "1 giorno", "bologna","Classico Contemporaneo"));
        lastId += 1;
        publicEvents.get(2).setId(lastId);
        publicEvents.add(new PublicEvent("Concerto Indie Acustico", false, LocalDate.of(2024, 8, 2), plannerDAO.planners.get(0),
                placesController.getPublicPlaces().get(0), "2 giorni", "firenze","Indie Acustico"));
        lastId += 1;
        publicEvents.get(3).setId(lastId);

    }

    public List<PublicEvent> getPublicEvents() {
        return publicEvents;
    }

    public List<PrivateEvent> getPrivateEvents() {
        return privateEvents;
    }


}
