package dao;

import model.Apple;
import model.Player;
import model.Snake;
import model.SnakePart;
import model.event.*;
import view.ScoreWindow;

import java.util.List;
import java.util.Random;

public class GameDao implements Dao {

    private int[][] board;

    private Snake snek;
    private Apple apple;
    private final Random valGenerator = new Random();
    private FileManager fileManager;

    public GameDao(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void initBoard(int rows, int cols) {
        if (board == null) {
            int[][] boardArr = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                int defVal = 0;
                for (int j = 0; j < cols; j++) {
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) defVal = 1;
                    boardArr[i][j] = defVal;
                    defVal = 0;
                }
            }
            board = boardArr;
        }
    }

    @Override
    public int[][] getBoard() {
        if (board == null) initBoard(10,10);
        return board;
    }

    @Override
    public void initSnake() {
        if (snek == null) {
            snek = new Snake(board[0].length / 2, board.length / 2);
            snek.addCellUpdatedListener(this);
            new Thread(snek).start();
        }
    }

    @Override
    public Snake getSnake() {
        if (snek == null) initSnake();
        return snek;
    }

    @Override
    public Apple getApple() {
        if (apple == null) initApple();
        return apple;
    }

    private void generateRandApple() {
        int randX = valGenerator.nextInt(0, board[0].length - 2);
        int randY = valGenerator.nextInt(0, board.length - 2);
        while (snek.hasPartOnCell(randX, randY) || randX == 0 || randY == 0) {
            randX = valGenerator.nextInt(0, board[0].length - 2);
            randY = valGenerator.nextInt(0, board.length - 2);
        }
        apple.setX(randX);
        apple.setY(randY);
        System.out.println("Apple spawned with x("+randX+") y("+randY+")");
    }

    private void initApple() {
        if (apple == null) {
            apple = new Apple(0, 0);
            generateRandApple();
            apple.addAppleSpawnedListeners(this);
            apple.addAppleSpawnedListeners(snek);
            apple.addAppleEatenListener(this);
        }
    }

    private void revalidateSnakePosition() {
        int headX = snek.getX();
        int headY = snek.getY();
        board[headY][headX] = 3;
        for (SnakePart p : snek.getNodes()) {
            board[p.getY()][p.getX()] = 2;
        }
    }

    @Override
    public void cellUpdated(CellUpdatedEvent evt) {
        revalidateSnakePosition();
        clearPreviousParts();
    }

    private void clearPreviousParts() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int el = board[i][j];
                if (el != 1 && el != 4 && !snek.hasPartOnCell(j, i)) board[i][j] = 0;
            }
        }
    }

    @Override
    public void appleEaten(AppleEatenEvent evt) {
        removeAppleFromBoard();
    }

    private void removeAppleFromBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                int el = board[i][j];
                if (el == 4) board[i][j] = 0;
            }
        }
        generateRandApple();
        apple.notifyAppleSpawnedListeners();
    }

    @Override
    public void appleSpawned(AppleSpawnedEvent evt) {
        int val = board[apple.getY()][apple.getX()];
        if (val != 0) removeAppleFromBoard();
        board[apple.getY()][apple.getX()] = 4;
        if (snek == null) initSnake();
        apple.addAppleSpawnedListeners(snek);
    }

    @Override
    public void scoreWindowOpened(ScoreWindowOpenedEvent evt) {
        ScoreWindow scoreWindow = (ScoreWindow) evt.getSource();
        scoreWindow.addAllPlayers(getPlayersFromBinaryFile());
        scoreWindow.addPlayerScore(snek.getScore());
        scoreWindow.addSaveButtonClickedListeners(this);
    }

    private List<Player> getPlayersFromBinaryFile() {
        return fileManager.retrieveFromBin();
    }

    @Override
    public void saveButtonClicked(SaveButtonClickedEvent evt) {
        if (evt.getSource() == null) return;
        String name = (String) evt.getSource();
        if (name.isEmpty()) return;
        savePlayerToBinaryFile(name);
    }

    private void savePlayerToBinaryFile(String name) {
        System.out.println("Saving player with name - " + name);
        Player thisPlayer = new Player(name, snek.getScore());
        fileManager.saveToBin(thisPlayer);
    }
}
