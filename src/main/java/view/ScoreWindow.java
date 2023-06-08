package view;

import model.Player;
import model.event.SaveButtonClickedEvent;
import model.listener.SaveButtonClickedListener;

import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ScoreWindow extends JFrame {

    private JTextArea scoreArea;
    private JTextArea infoArea;
    private JTextArea playerScoreArea;
    private JTextField nameField;
    private JButton saveButton;
    private List<Player> playerList = new CopyOnWriteArrayList<>();
    private final List<SaveButtonClickedListener> saveButtonClickedListeners = new CopyOnWriteArrayList<>();

    public ScoreWindow() throws HeadlessException {
        super();
        scoreArea = new JTextArea();
        infoArea = new JTextArea("Write your name here: ");
        nameField = new JTextField();
        saveButton = new JButton("Save score");
        playerScoreArea = new JTextArea("Your score -- ");
        Font font = new Font("Verdana", Font.BOLD, 14);

        scoreArea.setBounds(75, 90, 420, 190);
        scoreArea.setFont(font);
        scoreArea.setLayout(null);
        scoreArea.setEditable(false);
        scoreArea.setVisible(true);

        infoArea.setBounds(10, 10, 180, 30);
        infoArea.setFont(font);
        infoArea.setLayout(null);
        infoArea.setEditable(false);
        infoArea.setVisible(true);

        nameField.setBounds(200, 10, 160, 30);
        nameField.setFont(font);
        nameField.setLayout(null);
        nameField.setVisible(true);

        saveButton.setBounds(200, 290, 160, 30);
        saveButton.setFont(font);
        saveButton.setLayout(null);
        saveButton.addActionListener(e -> {
            notifySaveButtonClickedListeners();
            this.dispose();
        });
        saveButton.setVisible(true);

        playerScoreArea.setBounds(160, 50, 180, 30);
        playerScoreArea.setFont(font);
        playerScoreArea.setLayout(null);
        playerScoreArea.setEditable(false);
        playerScoreArea.setVisible(true);

        this.getContentPane().add(playerScoreArea);
        this.getContentPane().add(infoArea);
        this.getContentPane().add(nameField);
        this.getContentPane().add(saveButton);
        this.getContentPane().add(scoreArea);

        this.setLayout(null);
        this.setBounds(320, 200, 600, 380);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void notifySaveButtonClickedListeners() {
        saveButtonClickedListeners.forEach(listener -> listener.saveButtonClicked(new SaveButtonClickedEvent(nameField.getText())));
    }

    public void addAllPlayers(List<Player> playerList) {
        this.playerList.addAll(playerList);
        refreshTextArea();
    }

    public void addPlayerScore(int score) {
        playerScoreArea.setText(playerScoreArea.getText() + score);
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
        scoreArea.setText(builder.toString());
        repaint();
    }

    public void addSaveButtonClickedListeners(SaveButtonClickedListener listener) {
        saveButtonClickedListeners.add(listener);
    }
}
