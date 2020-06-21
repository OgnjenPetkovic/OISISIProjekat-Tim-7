package view.lekovi;

import view.AbstractContentPanel;

@SuppressWarnings("serial")
public class LekoviContentPanel extends AbstractContentPanel {
	
	String[] pretrage = {"Šifra leka", "Ime leka", "Proizvođač", "Opseg cene"};
	
	public LekoviContentPanel(LekoviPanel parent) {
		super(parent);
		details = new LekoviDetailsPanel(this);
		searchPanel = new LekoviSearchPanel(this);
		initGui();
	}
	
}
