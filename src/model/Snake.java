package model;

import model.event.CellUpdatedEvent;
import model.event.GameEndedEvent;
import model.listener.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Snake extends KeyAdapter implements Runnable, Pausable {

    private int x;
    private int y;
    private final static int initialSize = 5;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = true;
    private boolean isDown = false;
    private boolean terminated = false;
    private boolean paused = false;
    private final Object pauseLock = new Object();
    private List<SnakePart> nodeList = new CopyOnWriteArrayList<>();
    private List<CellUpdatedListener> cellUpdatedListeners = new CopyOnWriteArrayList<>();
    private List<GameEndedListener> gameEndedListeners = new CopyOnWriteArrayList<>();
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        initNodes();
    }

    public void addCellUpdatedListener(CellUpdatedListener listener) {
        cellUpdatedListeners.add(listener);
    }

    public void addGameEndedListener(GameEndedListener listener) {
        gameEndedListeners.add(listener);
    }

    private void initNodes() {
        for (int j = 1; j <= initialSize; j++) {
            nodeList.add(new SnakePart(x, y + j));
        }
    }

    @Override
    public void run() {
        while (!terminated) {
            try {
                synchronized (pauseLock) {
                    if (terminated) break;
                    if (paused) {
                        pauseLock.wait();
                    }
                    if (terminated) break;
                }
                Thread.sleep(500);
                if (terminated) break;
                if (paused) continue;
                move();
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep/wait. Exiting...");
                terminated = true;
            }
        }
        System.out.println("Snake terminated successfully.");
    }

    public void move() {
        if (isUp && !isDown) moveUp();
        if (isDown && !isUp) moveDown();
        if (isLeft && !isRight) moveLeft();
        if (isRight && !isLeft) moveRight();
//        nodeList.forEach(node -> System.out.println(node.getX() + "-x y-" + node.getY()));
        notifyCellUpdatedListeners();
    }

    private void shiftParts(int x, int y) {
        AtomicInteger prevX = new AtomicInteger(x);
        AtomicInteger prevY = new AtomicInteger(y);
        nodeList.forEach(node -> {
            int prevNodeX = node.getX();
            int prevNodeY = node.getY();
            node.setX(prevX.get());
            node.setY(prevY.get());
            prevX.set(prevNodeX);
            prevY.set(prevNodeY);
        });
    }

    private void moveRight() {
        shiftParts(x, y);
        x++;
        if (x == 17) {
            pause();
            gameEndedListeners.forEach(listener -> listener.gameEnded(new GameEndedEvent(this)));
        }
    }

    private void moveLeft() {
        shiftParts(x, y);
        x--;
        if (x == 0) {
            pause();
            gameEndedListeners.forEach(listener -> listener.gameEnded(new GameEndedEvent(this)));
        }
    }

    private void moveDown() {
        shiftParts(x, y);
        y++;
        if (y == 26) {
            pause();
            gameEndedListeners.forEach(listener -> listener.gameEnded(new GameEndedEvent(this)));
        }
    }

    private void moveUp() {
        shiftParts(x, y);
        y--;
        if (y == 0) {
            pause();
            gameEndedListeners.forEach(listener -> listener.gameEnded(new GameEndedEvent(this)));
        }
    }

    private void notifyCellUpdatedListeners() {
        cellUpdatedListeners.forEach(listener -> listener.cellUpdated(new CellUpdatedEvent(this)));
    }


    @Override
    public void pause() {
        System.out.println("Pausing snake.");
        paused = true;
    }

    @Override
    public void unPause() {
        System.out.println("Resuming snake.");
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notify();
        }
    }

    @Override
    public void terminate() {
        System.out.println("Terminating snake.");
        terminated = true;
        if (paused) unPause();
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

    public boolean hasPartOnCell(int x, int y) {
        if (this.x == x && this.y == y) return true;
        return nodeList.stream().anyMatch(snakePart -> snakePart.getX() == x && snakePart.getY() == y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP && !isDown) {
            isUp = true;
            isLeft = false;
            isRight = false;
        }
        if (code == KeyEvent.VK_DOWN && !isUp) {
            isDown = true;
            isLeft = false;
            isRight = false;
        }
        if (code == KeyEvent.VK_LEFT && !isRight) {
            isLeft = true;
            isUp = false;
            isDown = false;
        }
        if (code == KeyEvent.VK_RIGHT && !isLeft) {
            isRight = true;
            isUp = false;
            isDown = false;
        }
    }
}
