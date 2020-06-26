package view.recepti;

import view.AbstractPanel;

@SuppressWarnings("serial")
public class ReceptiPanel extends AbstractPanel {
	
	public ReceptiPanel(boolean readonly) {
		tablePanel = new ReceptiTablePanel(this);
		contentPanel = new ReceptiContentPanel(this, readonly);
		initGui();
	}

}
