package view.korisnici;

import view.AbstractContentPanel;

@SuppressWarnings("serial")
public class KorisniciContentPanel extends AbstractContentPanel {
	
	public KorisniciContentPanel(KorisniciPanel parent) {
		super(parent);
		details = new KorisniciDetailsPanel(this);
		searchPanel = new KorisniciSearchPanel(this);
		initGui();
	}
	
}
