package view;

import model.Player;

import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ScoreWindow extends JFrame {

    private JTextArea textArea;
    private List<Player> playerList = new CopyOnWriteArrayList<>();

    public ScoreWindow() throws HeadlessException {
        super();
        textArea = new JTextArea("Hi there!");
        Font font = new Font("Verdana", Font.BOLD, 14);

        textArea.setBounds(75, 60, 420, 360);
        textArea.setFont(font);
        textArea.setLayout(null);
        textArea.setEditable(false);
        textArea.setVisible(true);

        this.getContentPane().add(textArea);

        this.setLayout(null);
        this.setBounds(320, 200, 600, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addAllPlayers(List<Player> playerList) {
        this.playerList.addAll(playerList);
        refreshTextArea();
    }

    private void refreshTextArea() {
        StringBuilder builder = new StringBuilder();
        playerList = playerList.stream().sorted(Comparator.comparingInt(Player::getScore).reversed()).collect(Collectors.toList());
        int counter = 1;
        for (Player p : playerList) {
            builder.append(counter).append(". ").append(p.getName()).append(" - ").append(p.getScore()).append("\n");
            counter++;
            if (counter == 11) break;
        }
        textArea.setText(builder.toString());
        repaint();
    }
}
