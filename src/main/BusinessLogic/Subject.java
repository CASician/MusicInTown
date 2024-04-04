package main.BusinessLogic;



public interface Subject {
    void notifyEventObservers(int eventId) throws Exception;

    void notifyPlaceObservers(int placeId) throws Exception;

}
