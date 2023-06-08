package model.listener;

import model.event.AppleSpawnedEvent;

import java.util.EventListener;

public interface AppleSpawnedListener extends EventListener {

    void appleSpawned(AppleSpawnedEvent evt);
}
