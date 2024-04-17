package main.BusinessLogic;

import main.DomainModel.Event;

import java.sql.SQLException;

public interface Observer {
    void update(Event event) throws SQLException;
}
