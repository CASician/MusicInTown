package main.BusinessLogic;


import main.DomainModel.Event;

import java.util.ArrayList;

public interface Subject {

    void notifyEventObservers(Event event) throws Exception;

    void notifyPlaceObservers(int placeId) throws Exception;

}
