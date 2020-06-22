package view.korisnici;

import view.AbstractContentPanel;

@SuppressWarnings("serial")
public class KorisniciContentPanel extends AbstractContentPanel {
	
	String[] pretrage = {"Ime", "Prezime", "Tip"};
	
	public KorisniciContentPanel(KorisniciPanel parent) {
		super(parent);
		details = new KorisniciDetailsPanel(this);
		searchPanel = new KorisniciSearchPanel(this);
		initGui();
	}
	
}
