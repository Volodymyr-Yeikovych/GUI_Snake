package view;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board extends JTable {

    private static final int BORDER_PIXEL_SIZE = 32;
    private static final Color BORDER_COLOR = Color.LIGHT_GRAY;
    private static final Color SNAKE_HEAD_COLOR = Color.RED;
    private static final Color SNAKE_PART_COLOR = Color.ORANGE;
    private static final Color APPLE_COLOR = Color.GREEN;
    private static final Color BOARD_COLOR = Color.WHITE;
    private int WIDTH = 576;
    private int HEIGHT = 864;
    private int[][] intArray;
    public Board(int startX, int startY, Object[][] boardArray) {
        super(boardArray, getDummyObjectFromColumnSize(boardArray.length));

        this.setLayout(null);
        this.setBounds(startX, startY, WIDTH, HEIGHT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setMaxWidth(BORDER_PIXEL_SIZE);
        }
        this.setRowHeight(BORDER_PIXEL_SIZE);
        this.setVisible(true);
    }

    private static Object[] getDummyObjectFromColumnSize(int columnCount) {
        Object[] dummy = new Object[columnCount];
        Arrays.fill(dummy, 1);
        return dummy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoardBorders();
    }

    private void drawBoardBorders() {
        int lastColIndex = this.getColumnCount();
        for (int i = 0; i < lastColIndex; i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(new TableColorRender(BORDER_COLOR, SNAKE_HEAD_COLOR, SNAKE_PART_COLOR, APPLE_COLOR, BOARD_COLOR, intArray));
        }
    }

    public void addBoardArray(int[][] boardArray) {
        this.intArray = boardArray;
    }
}

