package view;

import javax.swing.*;

public class Board extends JPanel {

    public Board() {
        super();

        this.setBounds(320, 320, 320, 320);
        this.add(new JButton("BUTTON"));
        this.setVisible(true);
    }
}

