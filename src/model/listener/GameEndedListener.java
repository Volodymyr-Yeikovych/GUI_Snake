package model.listener;

import model.event.GameEndedEvent;

public interface GameEndedListener {
    void gameEnded(GameEndedEvent gameEndedEvent);
}
