package view.util.filters;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import view.util.Utility;

public class CustomJMBGFilter extends DocumentFilter {
	
	private Pattern jmbgPattern;
	
	public CustomJMBGFilter() {
		jmbgPattern = Pattern.compile("[0-9]*");
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
		String valueString = sb.replace(offset, offset + length, text).toString();

		if (valueString.isEmpty() || jmbgPattern.matcher(valueString).matches()) {
			if (valueString.length() <= 13) {
				super.replace(fb, offset, length, text, attrs);
			} else {
				Utility.showErrorMessage("JMBG ne sme biti duži od 13 cifara!");
			}
		} else {
			Utility.showErrorMessage("JMBG mora sadržati samo brojeve!");
		}
	}

}
