package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractContentPanel extends JPanel {
	
	protected AbstractPanel parent;
	protected AbstractDetailsPanel details;
	protected AbstractSearchPanel searchPanel;
	
	protected AbstractContentPanel(AbstractPanel parent) {
		this.parent = parent;
	}
	
	protected void initGui() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(300,550));
		this.setMaximumSize(new Dimension(500,1080));
		this.setBackground(Color.GRAY);
		
		details.setMinimumSize(new Dimension(300,300));
		details.setMaximumSize(new Dimension(500,600));
		
		searchPanel.setMinimumSize(new Dimension(300,300));
		searchPanel.setMaximumSize(new Dimension(500,600));
		
		this.add(details, BorderLayout.NORTH);
		this.add(searchPanel, BorderLayout.SOUTH);
	}
	
	public AbstractPanel getParent() {
		return parent;
	}
	
	public AbstractDetailsPanel getDetails() {
		return details;
	}
	
	public AbstractSearchPanel getSearchPanel() {
		return searchPanel;
	}
}
