package model.listener;

import model.event.GameEndedEvent;

import java.util.EventListener;

public interface GameEndedListener extends EventListener {
    void gameEnded(GameEndedEvent gameEndedEvent);
}
