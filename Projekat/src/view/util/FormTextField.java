package view.util;

import java.awt.Dimension;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormTextField extends JTextField {
	
	public FormTextField() {
		super();
		setPreferredSize(new Dimension(100,30));
	}

}
