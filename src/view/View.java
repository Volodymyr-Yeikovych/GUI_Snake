package view;

import model.Snake;

public interface View {
    void displayBoard(int[][] boardArray);

    void displaySnake(Snake snake);

    void refresh();

}
