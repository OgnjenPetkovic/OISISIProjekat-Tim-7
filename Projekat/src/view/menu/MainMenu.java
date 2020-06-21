package view.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;

import gui.MainWindow;
import gui.util.ContentPanelType;


@SuppressWarnings("serial")
public class MainMenu extends JToolBar {

	public MainMenu() {
		this.setBackground(Color.decode("#525252"));
		this.setFloatable(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(null);
				
		add(Box.createVerticalGlue());
		MenuButton btnLekovi= new MenuButton("images/lekovi.png");
		MenuButton btnRecepti = new MenuButton("images/recepti.png");
		add(btnLekovi);
		add(btnRecepti);
		btnLekovi.addActionListener(new BtnLekoviActionListener());
		btnRecepti.addActionListener(new BtnReceptiActionListener());
		

	}
	
	class BtnLekoviActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.LEKOVI);
		}
	}
	
	class BtnReceptiActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.RECEPTI);
		}
	}
}
