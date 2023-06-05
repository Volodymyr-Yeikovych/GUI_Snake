package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Snake implements Runnable, Pausable {

    private int x;
    private int y;
    private final static int initialSize = 5;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = true;
    private boolean isDown = false;
    private boolean terminated = false;
    private boolean paused = false;
    private List<SnakePart> nodeList = new CopyOnWriteArrayList<>();
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        initNodes();
    }

    private void initNodes() {
        for (int j = 1; j <= initialSize; j++) {
            nodeList.add(new SnakePart(x, y + j));
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        move();
    }

    public void move() {
        for(SnakePart p : nodeList) {
            System.out.println(p.getX() + " " + p.getY());
        }
    }


    @Override
    public void pause() {

    }

    @Override
    public void unPause() {

    }

    @Override
    public void terminate() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<SnakePart> getNodes() {
        return nodeList;
    }
}
