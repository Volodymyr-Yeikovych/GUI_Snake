package model.event;

import java.util.EventObject;

public class CellUpdatedEvent extends EventObject {
    public CellUpdatedEvent(Object source) {
        super(source);
    }
}
