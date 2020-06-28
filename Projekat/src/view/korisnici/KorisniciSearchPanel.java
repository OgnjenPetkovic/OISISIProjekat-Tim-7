package view.korisnici;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.RowFilter;

import model.util.TipKorisnika;
import view.AbstractSearchPanel;
import view.util.FormLabel;
import view.util.FormTextField;
import view.util.renderers.CustomComboBoxRenderer;

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
		TipKorisnika[] items = {TipKorisnika.NONE, TipKorisnika.APOTEKAR, TipKorisnika.LEKAR, TipKorisnika.ADMIN};
		tipCbx = new JComboBox<>(items);
		tipCbx.setRenderer(new CustomComboBoxRenderer());
		tipCbx.getRenderer();
		add(tipCbx, gBagC);
		
		gBagC.gridy++;
		gBagC.gridx = 0;
		add(search, gBagC);
		gBagC.gridx+=2;
		add(clear, gBagC);
		
	}

	@Override
	protected void clear() {
		super.clear();
		imeTxtFld.setText("");
		prezimeTxtFld.setText("");
		tipCbx.setSelectedItem(null);
		parent.getParent().getTablePanel().clearRowFilters();
	}

	@Override
	protected void search() {
		super.search();
		ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
		if (!imeTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + imeTxtFld.getText(), 0));
		}
		if (!prezimeTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + prezimeTxtFld.getText(), 1));
		}
		TipKorisnika userType = (TipKorisnika) tipCbx.getSelectedItem();
		if (userType != null && !TipKorisnika.NONE.equals(userType)) {
			filters.add(RowFilter.regexFilter("(?i)" + userType, 4));
		}
		parent.getParent().getTablePanel().filterRows(filters);
	}
	
}
