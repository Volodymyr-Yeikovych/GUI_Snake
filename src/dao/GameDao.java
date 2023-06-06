package dao;

import model.Snake;
import model.SnakePart;
import model.event.CellUpdatedEvent;
import model.listener.CellUpdatedListener;

public class GameDao implements Dao, CellUpdatedListener {

    private int[][] board;

    private Snake snek;

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
}
