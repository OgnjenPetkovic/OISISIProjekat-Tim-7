package view.lekovi;

import view.AbstractPanel;

@SuppressWarnings("serial")
public class LekoviPanel extends AbstractPanel {
	
	public LekoviPanel() {
		tablePanel = new LekoviTablePanel(this);
		contentPanel = new LekoviContentPanel(this);
		initGui();
	}

}
