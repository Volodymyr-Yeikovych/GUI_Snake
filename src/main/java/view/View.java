package view;

import model.Apple;
import model.Snake;
import model.listener.*;


public interface View extends CellUpdatedListener, GameEndedListener, AppleSpawnedListener, AppleEatenListener {
    void displayBoard(int[][] boardArray);

    void displaySnake(Snake snake);

    void refresh();

    void displayApple(Apple apple);

    void addScoreWindowOpenedListener(ScoreWindowOpenedListener listener);
}
