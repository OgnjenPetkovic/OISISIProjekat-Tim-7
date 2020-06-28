package view.lekovi;

import view.AbstractContentPanel;

@SuppressWarnings("serial")
public class LekoviContentPanel extends AbstractContentPanel {
	
	public LekoviContentPanel(LekoviPanel parent, boolean readonly) {
		super(parent);
		details = new LekoviDetailsPanel(this, readonly);
		searchPanel = new LekoviSearchPanel(this);
		initGui();
	}
	
}
