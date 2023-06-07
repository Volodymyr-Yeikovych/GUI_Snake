package model.listener;

import model.event.ScoreWindowOpenedEvent;

import java.util.EventListener;

public interface ScoreWindowOpenedListener extends EventListener {

    void scoreWindowOpened(ScoreWindowOpenedEvent evt);
}
