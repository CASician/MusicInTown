package main.BusinessLogic;



public interface Subject {
    void attach(Observer o);
    void detach(Observer o);

    void notifyEventObservers(int eventId) throws Exception;

    void notifyPlaceObservers(int placeId) throws Exception;

}
