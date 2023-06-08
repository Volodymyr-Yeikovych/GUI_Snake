package dao;

import model.Apple;
import model.Snake;
import model.listener.*;

public interface Dao extends ScoreWindowOpenedListener, CellUpdatedListener, AppleSpawnedListener, AppleEatenListener, SaveButtonClickedListener {
    void initBoard(int rows, int cols);
    int[][] getBoard();

    void initSnake();

    Snake getSnake();

    Apple getApple();
}
