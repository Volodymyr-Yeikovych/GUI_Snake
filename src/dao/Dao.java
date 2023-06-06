package dao;

import model.Snake;
import view.Board;

public interface Dao {
    void initBoard(int rows, int cols);
    int[][] getBoard();

    void initSnake();

    Snake getSnake();
}
