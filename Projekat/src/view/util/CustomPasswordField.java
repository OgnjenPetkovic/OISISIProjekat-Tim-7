package view.util;

import java.awt.Dimension;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class CustomPasswordField extends JPasswordField {

	public CustomPasswordField() {
		super();
		setPreferredSize(new Dimension(100,30));
	}

}
