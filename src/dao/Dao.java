package dao;

import model.Apple;
import model.Snake;
import model.listener.ScoreWindowOpenedListener;
import view.Board;

public interface Dao extends ScoreWindowOpenedListener {
    void initBoard(int rows, int cols);
    int[][] getBoard();

    void initSnake();

    Snake getSnake();

    Apple getApple();
}
