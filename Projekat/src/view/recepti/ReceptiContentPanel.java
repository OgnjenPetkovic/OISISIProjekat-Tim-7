package view.recepti;

import view.AbstractContentPanel;

@SuppressWarnings("serial")
public class ReceptiContentPanel extends AbstractContentPanel {

	public ReceptiContentPanel(ReceptiPanel parent, boolean readonly) {
		super(parent);
		details = new ReceptiDetailsPanel(this, readonly);
		searchPanel = new ReceptiSearchPanel(this);
		initGui();
	}

}
