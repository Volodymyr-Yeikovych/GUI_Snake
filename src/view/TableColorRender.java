package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRender extends DefaultTableCellRenderer {
    private Color background;
    public TableColorRender(Color background) {
        this.background = background;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == (Object) 1) {
            cell.setBackground(background);
//            cell.setForeground(background);
        } else if (value == (Object) 3) {
            cell.setBackground(Color.RED);
//            cell.setForeground(Color.RED);
        } else if (value == (Object) 2) {
            cell.setBackground(Color.ORANGE);
//            cell.setForeground(Color.ORANGE);
        } else {
            cell.setBackground(Color.WHITE);
//            cell.setForeground(Color.white);
        }
        return cell;
    }
}
