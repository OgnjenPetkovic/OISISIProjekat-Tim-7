package view.korpa;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KorpaPanel extends JPanel {
	
	private KorpaTablePanel tablePanel;
	private KorpaCheckoutPanel checkoutPanel;
	
	public KorpaPanel() {
		this.tablePanel = new KorpaTablePanel();
		this.checkoutPanel = new KorpaCheckoutPanel(this);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(tablePanel, BorderLayout.WEST);
		add(checkoutPanel, BorderLayout.EAST);
	}

	public KorpaTablePanel getTablePanel() {
		return tablePanel;
	}

	public KorpaCheckoutPanel getCheckoutPanel() {
		return checkoutPanel;
	}

}
