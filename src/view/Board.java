package view;

import model.listener.CellUpdatedListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board extends JTable {

    private static final int BORDER_PIXEL_SIZE = 32;
    private static final Color BORDER_COLOR = Color.lightGray;
    private int WIDTH = 576;
    private int HEIGHT = 864;
    private int[][] intArray;
    private List<CellUpdatedListener> cellListeners = new CopyOnWriteArrayList<>();

    public Board(int startX, int startY, Object[][] boardArray) {
        super(boardArray, getDummyObjectFromColumnSize(boardArray.length));

        this.setLayout(null);
        this.setBounds(startX, startY, WIDTH, HEIGHT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setMaxWidth(BORDER_PIXEL_SIZE);
        }
//        this.setShowGrid(false);
        this.setRowHeight(BORDER_PIXEL_SIZE);
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
        int lastColIndex = this.getColumnCount();
        for (int i = 0; i < lastColIndex; i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(new TableColorRender(BORDER_COLOR, intArray));
        }
    }

    public void addCellListener(CellUpdatedListener listener) {
        cellListeners.add(listener);
    }

    public void addBoardArray(int[][] boardArray) {
        this.intArray = boardArray;
    }
//    @Override
//    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//        Component cell = super.prepareRenderer(renderer, row, column);
//        return cell;
//    }
}

