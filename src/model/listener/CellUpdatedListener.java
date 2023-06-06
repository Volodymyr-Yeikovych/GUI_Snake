package model.listener;

import model.event.CellUpdatedEvent;

import java.util.EventListener;

public interface CellUpdatedListener extends EventListener {

    void cellUpdated(CellUpdatedEvent evt);
}
