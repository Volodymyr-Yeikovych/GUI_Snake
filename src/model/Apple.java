package model;

import dao.GameDao;
import model.event.AppleEatenEvent;
import model.event.AppleSpawnedEvent;
import model.listener.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Apple {
    private int x;
    private int y;
    private List<AppleSpawnedListener> spawnedListenerList = new CopyOnWriteArrayList<>();
    private List<AppleEatenListener> eatenListenerList = new CopyOnWriteArrayList<>();
    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void notifyAppleSpawnedListeners() {
        spawnedListenerList.forEach(listener -> listener.appleSpawned(new AppleSpawnedEvent(this)));
    }

    public void addAppleSpawnedListeners(AppleSpawnedListener listener) {
        spawnedListenerList.add(listener);
    }

    public void addAppleEatenListener(AppleEatenListener listener) {
        eatenListenerList.add(listener);
    }

    public void notifyAppleEatenListeners() {
        eatenListenerList.forEach(listener -> listener.appleEaten(new AppleEatenEvent(this)));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
