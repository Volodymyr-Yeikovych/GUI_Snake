package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRender extends DefaultTableCellRenderer {
    private Color borderColor;
    private Color snakeHeadColor;
    private Color snakePartsColor;
    private Color appleColor;
    private Color boardColor;
    private int[][] board;

    public TableColorRender(Color borderColor, Color snakeHeadColor, Color snakePartsColor, Color appleColor, Color boardColor,  int[][] board) {
        this.borderColor = borderColor;
        this.snakeHeadColor = snakeHeadColor;
        this.snakePartsColor = snakePartsColor;
        this.appleColor = appleColor;
        this.boardColor = boardColor;
        this.board = board;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Object val = column >= board[0].length || row >= board.length ? value : board[row][column];
        Component cell = super.getTableCellRendererComponent(table, val, isSelected, hasFocus, row, column);
        if (val == (Object) 1) {
            cell.setBackground(borderColor);
            cell.setForeground(borderColor);
        } else if (val == (Object) 2) {
            cell.setBackground(snakePartsColor);
            cell.setForeground(snakePartsColor);
        } else if (val == (Object) 3) {
            cell.setBackground(snakeHeadColor);
            cell.setForeground(snakeHeadColor);
        } else if (val == (Object) 4) {
            cell.setBackground(appleColor);
            cell.setForeground(appleColor);
        } else {
            cell.setBackground(boardColor);
            cell.setForeground(boardColor);
        }
        return cell;
    }
}
