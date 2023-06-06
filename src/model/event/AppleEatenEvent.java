package model.event;

import java.util.EventObject;

public class AppleEatenEvent extends EventObject {

    public AppleEatenEvent(Object source) {
        super(source);
    }
}
