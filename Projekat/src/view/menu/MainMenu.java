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
		add(btnLekovi);
		btnLekovi.addActionListener(new BtnLekoviActionListener());
		
		switch(loggedInUserType) {
			case ADMIN:{
				MenuButton btnReport = new MenuButton("images/dodatno.png", "images/dodatno_selektovano.png");
				MenuButton btnAdministrator = new MenuButton("images/administrator.png", "images/administrator_selektovano.png");
				btnAdministrator.addActionListener(new BtnAdministratorActionListener());
				btnReport.addActionListener(new BtnReportActionListener());
				add(btnReport);
				add(btnAdministrator);
				break;
			}
			case LEKAR: {
				MenuButton btnRecepti = new MenuButton("images/recepti.png", "images/recepti_selektovano.png");
				add(btnRecepti);
				btnRecepti.addActionListener(new BtnReceptiActionListener());
				break;
			}
			case APOTEKAR:{
				MenuButton btnRecepti = new MenuButton("images/recepti.png", "images/recepti_selektovano.png");
				MenuButton btnKorpa = new MenuButton("images/korpa.png", "images/korpa_selektovano.png");
				btnRecepti.addActionListener(new BtnReceptiActionListener());
				btnKorpa.addActionListener(new BtnKorpaActionListener());
				add(btnRecepti);
				add(btnKorpa);
				break;
			}
			default: break;
		}
		add(Box.createVerticalGlue());
	}
	
	private class BtnLekoviActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.LEKOVI);
		}
	}
	
	private class BtnReceptiActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.RECEPTI);
		}
	}
	
	private class BtnAdministratorActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.ADMINISTRATOR);
		}
	}
	
	private class BtnKorpaActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.KORPA);
		}
	}
	
	private class BtnReportActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainWindow.getInstance().replaceCurrentContent(ContentPanelType.REPORT);
		}
		
	}
}
