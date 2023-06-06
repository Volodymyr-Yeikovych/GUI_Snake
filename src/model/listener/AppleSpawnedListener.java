package model.listener;

import model.Apple;
import model.event.AppleSpawnedEvent;

public interface AppleSpawnedListener {

    void appleSpawned(AppleSpawnedEvent evt);
}
