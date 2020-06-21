package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.util.ContentPanelType;
import model.Podaci;
import model.entity.Korisnik;
import model.util.TipKorisnika;
import view.lekovi.LekoviPanel;
import view.menu.MainMenu;
import view.recepti.ReceptiPanel;


@SuppressWarnings("serial")

public class MainWindow extends JFrame{
	
	private static MainWindow instance = null;

	ContentPanelType currentContent = ContentPanelType.INIT;
	HashMap<ContentPanelType, JPanel> contentPanels = new HashMap<ContentPanelType, JPanel>();
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		
		return instance;
	}
	
	public ImagePanel welcomeImg;
	
	private MainWindow() {
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		
		Dimension screensize = toolKit.getScreenSize();
		int screenWidth = screensize.width;
		int screenHeight = screensize.height;
		Dimension min = new Dimension (screenWidth/2,screenHeight/2+100);
		setSize(screenWidth/2, screenHeight/2+100);
		setMinimumSize(min);
		
		Korisnik loggedInUser = Podaci.getInstance().getLoggedInUser();
		TipKorisnika loggedInUserType = loggedInUser.getTip();
		String loggedInUserFullName = loggedInUser.getIme() 
				+ " " + loggedInUser.getPrezime();
		setTitle("Generic Pharmacy - " + loggedInUserFullName + " (" 
				+ loggedInUserType.getOpis() + ")");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		LekoviPanel lekoviContentPanel = new LekoviPanel();
		contentPanels.put(ContentPanelType.LEKOVI, lekoviContentPanel);
		ReceptiPanel receptiContentPanel = new ReceptiPanel();
		contentPanels.put(ContentPanelType.RECEPTI, receptiContentPanel);
		
		switch(loggedInUserType) {
			case ADMIN:{
				break;
			}
			case APOTEKAR:{
				break;
			}
			default: break;
		}
		
		MainMenu meni = new MainMenu(loggedInUserType);
		add(meni, BorderLayout.WEST);
		
		welcomeImg = new ImagePanel("images/pocetna.png");
		welcomeImg.setLayout(new BoxLayout(welcomeImg, BoxLayout.LINE_AXIS));
		welcomeImg.setPreferredSize(new Dimension(500,350));
		contentPanels.put(ContentPanelType.INIT, welcomeImg);
		add(welcomeImg);
		
		this.setVisible(true);
		this.addWindowListener(new MainWindowListener());
	}
	
	public void replaceCurrentContent(ContentPanelType newContent) {
		if (!currentContent.equals(newContent))
			remove(contentPanels.get(currentContent));
		add(contentPanels.get(newContent));
		currentContent = newContent;
		repaint();
		revalidate();
	}
	
	class ImagePanel extends JPanel {
		
		private Image img;
		
		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}
		
		public ImagePanel(Image img) {
			this.img = img;
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
					 		 (int)(this.getSize().getHeight()-img.getHeight(null))/2,null);
		}
	}

	class MainWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			Podaci.getInstance().saveData();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
		}
		
	}
}
