package view.util.renderers;

import java.awt.Component;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Podaci;
import model.util.TipKorisnika;

@SuppressWarnings("serial")
public class CustomCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof Date) {
			value = Podaci.getInstance().getSdf().format((Date)value);
		} else if (value instanceof Float) {
			value = Float.toString(((Float) value));
		} else if (value instanceof Integer) {
			value = Integer.toString(((Integer) value));
		} else if (value instanceof TipKorisnika) {
			value = ((TipKorisnika) value).getOpis();
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
}
