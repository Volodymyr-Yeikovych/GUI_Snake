package view;

import model.Apple;
import model.Snake;
import model.listener.ScoreWindowOpenedListener;


public interface View {
    void displayBoard(int[][] boardArray);

    void displaySnake(Snake snake);

    void refresh();

    void displayApple(Apple apple);

    void addScoreWindowOpenedListener(ScoreWindowOpenedListener listener);
}
