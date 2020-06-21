package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.MainWindow;
import view.util.DetailsFormState;
import view.util.FormButton;

@SuppressWarnings("serial")
public abstract class AbstractDetailsPanel extends JPanel {

	protected DetailsFormState state;
	protected AbstractContentPanel parent;
	protected GridBagConstraints gBagC;
	protected JButton add;
	protected JButton edit;
	protected JButton delete;
	protected JButton confirm;
	protected JButton cancel;
	
	protected AbstractDetailsPanel (AbstractContentPanel parent) {
		this.parent = parent;
		gBagC = new GridBagConstraints();
		add = new FormButton("Dodaj");
		edit = new FormButton("Izmeni");
		delete = new FormButton("Obriši");
		confirm = new FormButton("Potvrdi");
		cancel = new FormButton("Odustani");
	}
	
	protected void initGui() {
		setLayout(new GridBagLayout());
		setBackground(Color.decode("#565857"));
		
		gBagC.fill = GridBagConstraints.HORIZONTAL;
		gBagC.anchor = GridBagConstraints.PAGE_START;
		gBagC.gridy = -1;
		gBagC.weighty = 1.0;
		gBagC.insets = new Insets(5,3,5,3);
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmDeletion();
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
	}
	
	protected void addSpacing() {
		add(Box.createHorizontalGlue(), gBagC);
		add(Box.createVerticalGlue(), gBagC);
		add(Box.createHorizontalStrut(80), gBagC);
		add(Box.createVerticalStrut(20), gBagC);
	}
	
	protected void changeFormState(DetailsFormState newState) {
		state = newState;
		switch (newState) {
			case DETAILS:
				add.setVisible(true);
				edit.setVisible(true);
				delete.setVisible(true);
				confirm.setVisible(false);
				cancel.setVisible(false);
				break;
			case ADD:
			case EDIT:
				add.setVisible(false);
				edit.setVisible(false);
				delete.setVisible(false);
				confirm.setVisible(true);
				cancel.setVisible(true);
		}
	}
	
	protected void add() {
		clearFields();
		changeFormState(DetailsFormState.ADD);
	}
	
	protected void clearFields() {
	}

	protected void edit() {
		if (parent.getParent().getTablePanel().getTable().getSelectedRow() != -1) {	
			changeFormState(DetailsFormState.EDIT);
		}
	}
	
	private void confirmDeletion() {
		int selectedRow = parent.getParent().getTablePanel().getTable().getSelectedRow();
		if (DetailsFormState.DETAILS.equals(state) && selectedRow != -1) {
			int confirmation = JOptionPane.showConfirmDialog(
				    MainWindow.getInstance(),
				    "Da li ste sigurni?",
				    "Potvrda brisanja",
				    JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == confirmation) {
				delete();
			}
		}
	}
	
	protected void delete() {
		clearFields();
		parent.getParent().getTablePanel().getTableModel().fireTableDataChanged();
	}
	
	protected void confirm() {
		clearFields();
		parent.getParent().getTablePanel().getTableModel().fireTableDataChanged();
		changeFormState(DetailsFormState.DETAILS);
	}
	
	protected void cancel() {
		clearFields();
		parent.getParent().getTablePanel().getTable().clearSelection();
		changeFormState(DetailsFormState.DETAILS);
	}
	
	public void insertTableData(Object rowData) {
	}
	
	public AbstractContentPanel getParent() {
		return parent;
	}
	
	public DetailsFormState getState() {
		return state;
	}
}
