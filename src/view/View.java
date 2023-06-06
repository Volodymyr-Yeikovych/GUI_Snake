package view;

import model.Snake;

import java.awt.event.ActionListener;

public interface View extends ActionListener {
    void displayBoard(int[][] boardArray);

    void displaySnake(Snake snake);

    void refresh();

}
