package view.util;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FormButton extends JButton {
	
	public FormButton(String label) {
		super(label);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}
	
	public FormButton(String label, boolean visible) {
		this(label);
		setVisible(visible);
	}

}
