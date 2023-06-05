package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

public class Board extends JTable {

    private static final int BORDER_PIXEL_SIZE = 32;
    private static final Color BORDER_COLOR = Color.lightGray;
    private int WIDTH = 576;
    private int HEIGHT = 832;
    private Object[][] boardArray;

    public Board(int startX, int startY, Object[][] boardArray) {
        super(boardArray, getDummyObjectFromColumnSize(boardArray.length));
        this.boardArray = boardArray;

        this.setLayout(null);
        this.setBounds(startX, startY, WIDTH, HEIGHT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setMaxWidth(32);
        }
//        this.setShowGrid(false);
        this.setRowHeight(32);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Inside");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Out");
            }
        });
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
        drawBoardBorders(g);
    }

    private void drawBoardBorders(Graphics g) {
        int lastColIndex = this.getColumnCount() - 1;
        int lastRowIndex = this.getRowCount() - 1;
        for (int i = 0; i <= lastColIndex; i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(new TableColorRender(Color.lightGray, Color.white, lastColIndex, lastRowIndex));
        }
    }

//    @Override
//    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//        Component cell = super.prepareRenderer(renderer, row, column);
//        int lastColIndex = this.getColumnCount() - 1;
//        int lastRowIndex = this.getRowCount() - 1;
//        if (this.)
//        if (column == 0 || column == lastColIndex || row == 0 || row == lastRowIndex) {
//            cell.setBackground(BORDER_COLOR);
//        }
//        return cell;
//    }
}

