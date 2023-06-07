package model.listener;

import model.event.AppleEatenEvent;

import java.util.EventListener;

public interface AppleEatenListener extends EventListener {
    void appleEaten(AppleEatenEvent evt);
}
