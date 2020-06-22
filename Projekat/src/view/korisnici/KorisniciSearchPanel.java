package view.korisnici;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.RowFilter;

import model.util.TipKorisnika;
import view.AbstractSearchPanel;
import view.util.FormLabel;
import view.util.FormTextField;

@SuppressWarnings("serial")
public class KorisniciSearchPanel extends AbstractSearchPanel {
	
	private FormLabel imeLbl;
	private FormLabel prezimeLbl;
	private FormLabel tipLbl;
	private FormTextField imeTxtFld;
	private FormTextField prezimeTxtFld;
	private JComboBox<TipKorisnika> tipCbx;
	
	public KorisniciSearchPanel(KorisniciContentPanel parent) {
		super(parent);
		initGui();
		
		gBagC.gridx = 0;
		gBagC.gridy = 0;
		imeLbl = new FormLabel("Ime");
		add(imeLbl, gBagC);
		gBagC.gridx+=2;
		imeTxtFld = new FormTextField();
		add(imeTxtFld, gBagC);
	
		gBagC.gridx = 0;
		gBagC.gridy++; 
		prezimeLbl = new FormLabel("Prezime");
		add(prezimeLbl, gBagC);
		gBagC.gridx+=2;
		prezimeTxtFld = new FormTextField();
		add(prezimeTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		tipLbl = new FormLabel("Tip");
		add(tipLbl, gBagC);
		gBagC.gridx+=2;
		TipKorisnika[] items = {null, TipKorisnika.APOTEKAR, TipKorisnika.LEKAR, TipKorisnika.ADMIN};
		tipCbx = new JComboBox<>(items);
		add(tipCbx, gBagC);
		
		gBagC.gridy++;
		gBagC.gridx = 0;
		add(search, gBagC);
		gBagC.gridx+=2;
		add(clear, gBagC);
		
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
				if (!imeTxtFld.getText().isEmpty()) {
					filters.add(RowFilter.regexFilter("(?i)" + imeTxtFld.getText(), 0));
				}
				if (!prezimeTxtFld.getText().isEmpty()) {
					filters.add(RowFilter.regexFilter("(?i)" + prezimeTxtFld.getText(), 1));
				}
				TipKorisnika userType = (TipKorisnika) tipCbx.getSelectedItem();
				if (userType != null) {
					filters.add(RowFilter.regexFilter("(?i)" + userType.getOpis(), 4));
				}
				parent.getParent().getTablePanel().filterRows(filters);
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				imeTxtFld.setText("");
				prezimeTxtFld.setText("");
				tipCbx.setSelectedItem("");
				parent.getParent().getTablePanel().clearRowFilters();
			}
		});
	}
}
