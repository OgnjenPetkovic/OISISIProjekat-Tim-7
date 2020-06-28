package view.menu;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuButton extends JButton {
	
	private ImageIcon ic;
	private ImageIcon hoverIC;

	public MenuButton(String pathToImg, String hoverImg) {
		super("");
		ic = new ImageIcon(pathToImg);
		hoverIC = new ImageIcon(hoverImg);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		this.setMaximumSize(new Dimension (96,96));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		Image img = ic.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
		Image hover = hoverIC.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img, ic.getDescription());
		hoverIC = new ImageIcon(hover, hoverIC.getDescription());
		addMouseListener(new MenuButtonMouseListener());
		setIcon(ic);
	}
	
	private class MenuButtonMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent paramMouseEvent) {
		}

		@Override
		public void mouseEntered(MouseEvent paramMouseEvent) {
			setIcon(hoverIC);
		}

		@Override
		public void mouseExited(MouseEvent paramMouseEvent) {
			setIcon(ic);
		}

		@Override
		public void mousePressed(MouseEvent paramMouseEvent) {
		}

		@Override
		public void mouseReleased(MouseEvent paramMouseEvent) {
		}
		
	}
	
}
