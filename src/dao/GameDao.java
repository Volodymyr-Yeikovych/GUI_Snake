package dao;

import exception.InvalidAppleSpawnException;
import model.Apple;
import model.Snake;
import model.SnakePart;
import model.event.AppleEatenEvent;
import model.event.AppleSpawnedEvent;
import model.event.CellUpdatedEvent;
import model.listener.AppleEatenListener;
import model.listener.AppleSpawnedListener;
import model.listener.CellUpdatedListener;

import java.util.Random;

public class GameDao implements Dao, CellUpdatedListener, AppleSpawnedListener, AppleEatenListener {

    private int[][] board;

    private Snake snek;
    private Apple apple;

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

    private void initApple() {
        if (apple == null) {
            Random valGenerator = new Random();
            int randX = valGenerator.nextInt(0, board.length - 2);
            int randY = valGenerator.nextInt(0, board[0].length - 2);
            while (snek.hasPartOnCell(randX, randY) || randX == 0 || randY == 0) {
                randX = valGenerator.nextInt(0, board.length);
                randY = valGenerator.nextInt(0, board[0].length);
            }
            apple = new Apple(randX, randY);
            System.out.println(randX + "-x y-" + randY);
            apple.addAppleSpawnedListeners(this);
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
                if (el != 1 && !snek.hasPartOnCell(j, i)) board[i][j] = 0;
            }
        }
    }

    @Override
    public void appleEaten(AppleEatenEvent evt) {

    }

    @Override
    public void appleSpawned(AppleSpawnedEvent evt) {
        int val = board[apple.getX()][apple.getY()];
        if (val != 0) throw new InvalidAppleSpawnException("Trying to spawn apple in non empty cell.");
        board[apple.getX()][apple.getY()] = 4;
        System.out.println("SET");
    }
}
