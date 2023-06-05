package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRender extends DefaultTableCellRenderer {
    private Color background;
    private Color textCol;
    private int lastColumn;
    private int lastRow;
    public TableColorRender(Color background, Color textCol, int lastColumn, int lastRow) {
        this.background = background;
        this.textCol = textCol;
        this.lastColumn = lastColumn;
        this.lastRow = lastRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column == 0 || column == lastColumn || row == 0 || row == lastRow) {
            cell.setBackground(background);
            cell.setForeground(textCol);
        }
        return cell;
    }
}
