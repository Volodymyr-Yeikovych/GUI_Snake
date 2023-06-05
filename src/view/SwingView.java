package view;

import model.Snake;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements View {

    private JTable board;

    public SwingView() throws HeadlessException {
        super();

        this.setSize(1080, 1080);
        this.setResizable(true);
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
    }

    @Override
    public void displaySnake(Snake snake) {
    }

    @Override
    public void refresh() {
        this.repaint();
        board.repaint();
    }
}
