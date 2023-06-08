package model.event;

import jdk.jfr.Event;

import java.util.EventObject;

public class CellUpdatedEvent extends EventObject {
    public CellUpdatedEvent(Object source) {
        super(source);
    }
}
