package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {
	
	protected AbstractTablePanel tablePanel;
	protected AbstractContentPanel contentPanel;

	protected void initGui() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(tablePanel);
		add(contentPanel);
	}
	
	public AbstractTablePanel getTablePanel() {
		return tablePanel;
	}

	public AbstractContentPanel getContentPanel() {
		return contentPanel;
	}

}
