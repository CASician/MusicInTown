package main.BusinessLogic;


import main.DomainModel.Event;

import java.util.ArrayList;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);

    void notifyEventObservers(Event event) throws Exception;

    void notifyPlaceObservers(int placeId) throws Exception;

}
