package model.listener;

import model.event.AppleEatenEvent;

public interface AppleEatenListener {
    void appleEaten(AppleEatenEvent evt);
}
