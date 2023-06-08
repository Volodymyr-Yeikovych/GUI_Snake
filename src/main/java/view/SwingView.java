package view;

import model.Apple;
import model.Snake;
import model.event.*;
import model.listener.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class SwingView extends JFrame implements View, CellUpdatedListener, GameEndedListener, AppleSpawnedListener, AppleEatenListener {

    private Board board;
    private ScoreWindow scoreWindow;
    private List<ScoreWindowOpenedListener> scoreWindowOpenedListeners = new CopyOnWriteArrayList<>();
    public SwingView() throws HeadlessException {
        super();

        this.setSize(1080, 1080);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void displayBoard(int[][] boardArray) {
        Object[][] mapped = new Object[boardArray.length][boardArray[0].length];
        for (int i = 0; i < mapped.length; i++) {
            for (int j = 0; j < mapped[i].length; j++) {
                mapped[i][j] = boardArray[i][j];
            }
        }
        this.board = new Board(100, 100, mapped);
        this.getContentPane().add(board);
        board.addBoardArray(boardArray);
        board.addCellListener(this);

    }

    @Override
    public void displaySnake(Snake snake) {
        snake.addCellUpdatedListener(this);
        snake.addGameEndedListener(this);
        this.addKeyListener(snake);
    }

    @Override
    public void refresh() {
        this.repaint();
        board.repaint();
    }

    @Override
    public void displayApple(Apple apple) {
        apple.addAppleSpawnedListeners(this);
        apple.addAppleEatenListener(this);
        apple.notifyAppleSpawnedListeners();
    }

    @Override
    public void cellUpdated(CellUpdatedEvent evt) {
        refresh();
    }

    @Override
    public void gameEnded(GameEndedEvent gameEndedEvent) {
        Snake snek = (Snake) gameEndedEvent.getSource();
        snek.terminate();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.dispose();
        this.scoreWindow = new ScoreWindow();
        notifyScoreWindowOpenedListeners();
    //        Arrays.stream(Frame.getFrames()).forEach(Window::dispose);
    }
    @Override
    public void appleEaten(AppleEatenEvent evt) {
        refresh();
    }

    @Override
    public void appleSpawned(AppleSpawnedEvent evt) {
        refresh();
    }

    @Override
    public void addScoreWindowOpenedListener(ScoreWindowOpenedListener listener) {
        scoreWindowOpenedListeners.add(listener);
    }

    private void notifyScoreWindowOpenedListeners() {
        scoreWindowOpenedListeners.forEach(listener -> listener.scoreWindowOpened(new ScoreWindowOpenedEvent(scoreWindow)));
    }
}
