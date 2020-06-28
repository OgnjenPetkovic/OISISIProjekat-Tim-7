package view.util.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import model.util.TipKorisnika;

@SuppressWarnings("serial")
public class CustomComboBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, 
			boolean isSelected, boolean cellHasFocus) {
		if (value instanceof TipKorisnika) {
			value = ((TipKorisnika) value).getOpis();
		}
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}
