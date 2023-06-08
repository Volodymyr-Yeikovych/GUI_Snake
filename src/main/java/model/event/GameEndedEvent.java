package model.event;

import java.util.EventObject;

public class GameEndedEvent extends EventObject {

    public GameEndedEvent(Object source) {
        super(source);
    }
}
