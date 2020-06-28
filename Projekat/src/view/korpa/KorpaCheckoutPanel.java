package view.korpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Podaci;
import model.entity.KorpaItem;
import model.entity.Racun;
import view.util.FormButton;
import view.util.FormTextField;
import view.util.Utility;
import view.util.exceptions.RelatedRecordNotFound;
import view.util.filters.CustomIntegerFilter;

@SuppressWarnings("serial")
public class KorpaCheckoutPanel extends JPanel {
	
	private KorpaPanel parent;
	private GridBagConstraints gBagC;
	private JPanel contentPanel;
	private JPanel fillerPanelCenter;
	private JPanel fillerPanelSouth;
	private JRadioButton lekRB;
	private JRadioButton receptRB;
	private ButtonGroup rbGroup;
	private FormTextField lekTxtFld;
	private FormTextField kolicinaTxtFld;
	private FormTextField receptTxtFld;
	private FormButton add;
	private FormButton clear;
	private FormButton submit;
	
	private String rbState = "LEK"; 
	
	public KorpaCheckoutPanel(KorpaPanel parent) {
		this.parent = parent;
		setLayout(new BorderLayout());
		fillerPanelCenter = new JPanel();
		fillerPanelCenter.setBackground(Color.decode("#565857"));
		fillerPanelSouth = new JPanel();
		fillerPanelSouth.setBackground(Color.decode("#565857"));
		add(fillerPanelCenter, BorderLayout.CENTER);
		add(fillerPanelSouth, BorderLayout.SOUTH);
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		contentPanel.setBackground(Color.decode("#565857"));
		
		gBagC = new GridBagConstraints();
		gBagC.fill = GridBagConstraints.HORIZONTAL;
		gBagC.anchor = GridBagConstraints.PAGE_START;
		gBagC.gridy = -1;
		gBagC.weighty = 1.0;
		gBagC.insets = new Insets(5,3,5,3);
		
		rbGroup = new ButtonGroup();
		
		gBagC.gridx = 0;
		gBagC.gridy = 0;
		lekRB = new JRadioButton("Lek i količina");
		lekRB.setBackground(Color.decode("#565857"));
		lekRB.setForeground(Color.WHITE);
		lekRB.setActionCommand("LEK");
		lekRB.addActionListener(new KorpaRadioButtonActionListener());
		lekRB.setSelected(true);
		contentPanel.add(lekRB, gBagC);
		rbGroup.add(lekRB);
		gBagC.gridx+=1;
		lekTxtFld = new FormTextField();
		contentPanel.add(lekTxtFld, gBagC);
		gBagC.gridx+=1;
		kolicinaTxtFld = new FormTextField(new CustomIntegerFilter());
		contentPanel.add(kolicinaTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++;
		receptRB = new JRadioButton("Recept");
		receptRB.setBackground(Color.decode("#565857"));
		receptRB.setForeground(Color.WHITE);
		receptRB.setActionCommand("RECEPT");
		receptRB.addActionListener(new KorpaRadioButtonActionListener());
		contentPanel.add(receptRB, gBagC);
		rbGroup.add(receptRB);
		gBagC.gridx+=1;
		receptTxtFld = new FormTextField(new CustomIntegerFilter());
		receptTxtFld.setEditable(false);
		contentPanel.add(receptTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++;
		add = new FormButton("Dodaj");
		add.setActionCommand("ADD");
		add.addActionListener(new KorpaButtonActionListener());
		contentPanel.add(add, gBagC);
		gBagC.gridx+=1;
		clear = new FormButton("Očisti");
		clear.setActionCommand("CLEAR");
		clear.addActionListener(new KorpaButtonActionListener());
		contentPanel.add(clear, gBagC);
		gBagC.gridx+=1;
		submit = new FormButton("Završi");
		submit.setActionCommand("SUBMIT");
		submit.addActionListener(new KorpaButtonActionListener());
		contentPanel.add(submit, gBagC);
		add(contentPanel, BorderLayout.NORTH);
		
		this.setMaximumSize(new Dimension(contentPanel.getPreferredSize().width, 
				Short.MAX_VALUE));
	}
	
	private void addToCart() {
		if ("LEK".equals(rbState)) {
			if (lekTxtFld.getText().isEmpty() || kolicinaTxtFld.getText().isEmpty()) {
				Utility.showErrorMessage("Morate uneti šifru leka i količinu!");
			} else {
				try {
					parent.getTablePanel().updateUkupnoTxtFld(Podaci.getInstance().getKorpa()
							.addItem(lekTxtFld.getText(), Integer.parseInt(kolicinaTxtFld.getText())));
				} catch (RelatedRecordNotFound e) {
					Utility.showErrorMessage(e.getMessage());
				}
			}
		} else {
			if (receptTxtFld.getText().isEmpty()) {
				Utility.showErrorMessage("Morate uneti šifru recepta!");
			} else {
				try {
					parent.getTablePanel().updateUkupnoTxtFld(Podaci.getInstance().getKorpa()
							.addItem(Integer.parseInt(receptTxtFld.getText())));
				} catch (RelatedRecordNotFound e) {
					Utility.showErrorMessage(e.getMessage());
				}
			}
		}
		parent.getTablePanel().getTableModel().fireTableDataChanged();
	}
	
	private void clearCart() {
		Podaci.getInstance().getKorpa().clearCart();
		parent.getTablePanel().clearUkupnoTxtFld();
		parent.getTablePanel().getTableModel().fireTableDataChanged();
	}
	
	private void submitCart() {
		if (Podaci.getInstance().getKorpa().getRowCount() == 0) {
			Utility.showErrorMessage("Korpa je prazna!");
		} else {
			ArrayList<KorpaItem> racunItems = new ArrayList<>(Podaci.getInstance().getKorpa().getData());
			Podaci.getInstance().getRacuni().getData().add(
					new Racun(Podaci.getInstance().getLoggedInUser(), racunItems));
			clearCart();
			Utility.showInfoMessage("Kupovina uspešno završena!");
		}
	}
	
	private void clearFields() {
		lekTxtFld.setText("");
		kolicinaTxtFld.setText("");
		receptTxtFld.setText("");
	}
	
	private void rbOnChange(String actionCommand) {
		rbState = actionCommand;
		clearFields();
		if ("LEK".equals(actionCommand)) {
			lekTxtFld.setEditable(true);
			kolicinaTxtFld.setEditable(true);
			receptTxtFld.setEditable(false);
		} else {
			lekTxtFld.setEditable(false);
			kolicinaTxtFld.setEditable(false);
			receptTxtFld.setEditable(true);
		}
	}

	public JRadioButton getReceptRB() {
		return receptRB;
	}

	public FormTextField getLekTxtFld() {
		return lekTxtFld;
	}

	public FormTextField getKolicinaTxtFld() {
		return kolicinaTxtFld;
	}

	public FormTextField getReceptTxtFld() {
		return receptTxtFld;
	}
	
	private class KorpaRadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			rbOnChange(e.getActionCommand());
		}
		
	}
	
	private class KorpaButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("ADD".equals(e.getActionCommand())) {
				addToCart();
			} else if ("CLEAR".equals(e.getActionCommand())) {
				clearCart();
			} else {
				submitCart();
			}
		}
		
	}

}
