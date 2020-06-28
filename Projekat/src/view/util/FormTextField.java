package view.util;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class FormTextField extends JTextField {
	
	public FormTextField() {
		super();
		setPreferredSize(new Dimension(100,30));
	}
	
	public FormTextField(DocumentFilter df) {
		this();
		((PlainDocument) getDocument()).setDocumentFilter(df);
	}

}
