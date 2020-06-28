package view.util.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import view.util.Utility;

public class CustomFloatFilter extends DocumentFilter {
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
		String valueString = sb.replace(offset, offset + length, text).toString();

		if (valueString.isEmpty() || isFloat(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			Utility.showErrorMessage("Morate uneti broj!");
		}
	}

	private boolean isFloat(String valueString) {
		try {
			Float.parseFloat(valueString);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
