package main.BusinessLogic;

import main.DomainModel.Event;

public interface Observer {
    void update(Event event);
}
