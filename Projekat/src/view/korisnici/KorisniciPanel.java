package view.korisnici;

import view.AbstractPanel;

@SuppressWarnings("serial")
public class KorisniciPanel extends AbstractPanel {
	
	public KorisniciPanel() {
		tablePanel = new KorisniciTablePanel(this);
		contentPanel = new KorisniciContentPanel(this);
		initGui();
	}

}
