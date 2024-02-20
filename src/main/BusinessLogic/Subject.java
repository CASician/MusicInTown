package main.BusinessLogic;



public interface Subject {
    public void notifyEventObservers(int eventId) throws Exception;

    public void notifyPlaceObservers(int placeId) throws Exception;

}
