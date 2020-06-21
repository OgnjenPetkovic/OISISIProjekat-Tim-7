package view.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FormLabel extends JLabel {
	
	public FormLabel(String label) {
		super(label);
		setPreferredSize(new Dimension(100,30));
		setForeground(Color.WHITE);
	}

}
