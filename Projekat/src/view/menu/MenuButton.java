package view.menu;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuButton extends JButton {

	public MenuButton(String pathToImg) {
		super("");
		ImageIcon ic = new ImageIcon(pathToImg);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		this.setMaximumSize(new Dimension (96,96));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		Image img = ic.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img, ic.getDescription());
		
		setIcon(ic);
	}
	
}
