package view.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;

import gui.MainWindow;
import gui.util.ContentPanelType;
import model.util.TipKorisnika;

@SuppressWarnings("serial")
public class MainMenu extends JToolBar {

	public MainMenu(TipKorisnika loggedInUserType) {
		this.setBackground(Color.decode("#525252"));
		this.setFloatable(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(null);
				
		add(Box.createVerticalGlue());
		MenuButton btnLekovi= new MenuButton("images/lekovi.png", "images/lekovi_selektovano.png");
		MenuButton btnRecepti = new MenuButton("images/recepti.png", "images/recepti_selektovano.png");
		add(btnLekovi);
		add(btnRecepti);
		btnLekovi.addActionListener(new BtnLekoviActionListener());
		btnRecepti.addActionListener(new BtnReceptiActionListener());
		
		switch(loggedInUserType) {
			case ADMIN:{
				MenuButton btnLoyalty = new MenuButton("images/dodatno.png", "images/dodatno_selektovano.png");
				MenuButton btnAdministrator = new MenuButton("images/administrator.png", "images/administrator_selektovano.png");
				btnAdministrator.addActionListener(new BtnAdministratorActionListener());
				add(btnLoyalty);
				add(btnAdministrator);
				break;
			}
			case APOTEKAR:{
				MenuButton btnKorpa = new MenuButton("images/korpa.png", "images/korpa_selektovano.png");
				add(btnKorpa);
				break;
			}
			default: break;
		}
		add(Box.createVerticalGlue());
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
	
	class BtnAdministratorActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.ADMINISTRATOR);
		}
	}
}
