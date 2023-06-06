package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRender extends DefaultTableCellRenderer {
    private Color background;
    private int[][] board;
    public TableColorRender(Color background, int[][] board) {
        this.background = background;
        this.board = board;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Object val = column >= board[0].length || row >= board.length ? value : board[row][column];
        Component cell = super.getTableCellRendererComponent(table, val, isSelected, hasFocus, row, column);
        if (val == (Object) 1) {
            cell.setBackground(background);
//            cell.setForeground(background);
        } else if (val == (Object) 3) {
            cell.setBackground(Color.RED);
//            cell.setForeground(Color.RED);
        } else if (val == (Object) 2) {
            cell.setBackground(Color.ORANGE);
//            cell.setForeground(Color.ORANGE);
        } else {
            cell.setBackground(Color.WHITE);
//            cell.setForeground(Color.white);
        }
        return cell;
    }
}
