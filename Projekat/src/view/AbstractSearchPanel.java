package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;

import view.util.DetailsFormState;
import view.util.FormButton;

@SuppressWarnings("serial")
public abstract class AbstractSearchPanel extends JPanel {
	
	protected AbstractContentPanel parent;
	protected GridBagConstraints gBagC;
	protected FormButton search;
	protected FormButton clear;
	
	protected AbstractSearchPanel(AbstractContentPanel parent) {
		this.parent = parent;
		search = new FormButton("Pretraži", true);
		clear = new FormButton("Poništi", true);
	}
	
	protected void initGui() {
		setLayout(new GridBagLayout());
		setBackground(Color.decode("#6F7170"));
		
		gBagC = new GridBagConstraints();
		gBagC.fill = GridBagConstraints.HORIZONTAL;
		gBagC.anchor = GridBagConstraints.PAGE_START;
		gBagC.gridy = -1;
		gBagC.weighty = 1.0;
		gBagC.insets = new Insets(5,3,5,3);
		
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (DetailsFormState.DETAILS.equals(parent.getDetails().getState())) {
					search();
				}
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (DetailsFormState.DETAILS.equals(parent.getDetails().getState())) {
					clear();
				}
			}
		});
	}
	
	protected void addSpacing() {
		add(Box.createHorizontalGlue(), gBagC);
		add(Box.createVerticalGlue(), gBagC);
		add(Box.createHorizontalStrut(80), gBagC);
		add(Box.createVerticalStrut(20), gBagC);
	}
	
	protected void clear() {
		parent.getDetails().clearFields();
	}
	
	protected void search() {
		parent.getDetails().clearFields();
	}
	
}
