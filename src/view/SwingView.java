package view;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements View{

    private JPanel board;

    public SwingView() throws HeadlessException {
        super();

        this.board = new Board();
        this.getContentPane().add(board);

        this.setSize(1080, 1080);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void displayBoard() {

    }
}
