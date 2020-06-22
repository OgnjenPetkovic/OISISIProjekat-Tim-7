package view.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
 
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableHeader implements TableCellRenderer {
     
    private TableCellRenderer defaultRenderer;
     
    public TableHeader(TableCellRenderer defaultRenderer) {
        this.defaultRenderer = defaultRenderer;
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        if (comp instanceof JLabel) {
            JLabel label = (JLabel) comp;
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.GRAY);
            label.setBorder(BorderFactory.createEtchedBorder());
        }
         
        return comp;
    }
 
}