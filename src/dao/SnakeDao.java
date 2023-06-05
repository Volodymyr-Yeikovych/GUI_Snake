package dao;

import java.util.Arrays;

public class SnakeDao implements Dao{
    @Override
    public int[][] getBoardArray(int rows, int cols) {
        int[][] boardArr = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(boardArr[i], 0);
        }
        return boardArr;
    }
}
