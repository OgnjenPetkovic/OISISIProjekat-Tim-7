package view.recepti;

import java.util.ArrayList;

import javax.swing.RowFilter;

import view.AbstractSearchPanel;
import view.util.FormLabel;
import view.util.FormTextField;

@SuppressWarnings("serial")
public class ReceptiSearchPanel extends AbstractSearchPanel {

	private FormLabel sifraLbl;
	private FormLabel lekarLbl;
	private FormLabel jmbgLbl;
	private FormLabel lekLbl;
	private FormTextField sifraTxtFld;
	private FormTextField lekarTxtFld;
	private FormTextField jmbgTxtFld;
	private FormTextField lekTxtFld;
	
	public ReceptiSearchPanel(ReceptiContentPanel parent) {
		super(parent);
		initGui();
		
		gBagC.gridx = 0;
		gBagC.gridy = 0;
		sifraLbl = new FormLabel("Šifra");
		add(sifraLbl, gBagC);
		gBagC.gridx+=2;
		sifraTxtFld = new FormTextField();
		add(sifraTxtFld, gBagC);
	
		gBagC.gridx = 0;
		gBagC.gridy++; 
		lekarLbl = new FormLabel("Lekar");
		add(lekarLbl, gBagC);
		gBagC.gridx+=2;
		lekarTxtFld = new FormTextField();
		add(lekarTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		jmbgLbl = new FormLabel("JMBG pacijenta");
		add(jmbgLbl, gBagC);
		gBagC.gridx+=2;
		jmbgTxtFld = new FormTextField();
		add(jmbgTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		lekLbl = new FormLabel("Šifra leka");
		add(lekLbl, gBagC);
		gBagC.gridx+=2;
		lekTxtFld = new FormTextField();
		add(lekTxtFld, gBagC);
		
		gBagC.gridy++;
		gBagC.gridx = 0;
		add(search, gBagC);
		gBagC.gridx+=2;
		add(clear, gBagC);
		
	}
	
	@Override
	protected void clear() {
		super.clear();
		sifraTxtFld.setText("");
		lekarTxtFld.setText("");
		jmbgTxtFld.setText("");
		lekTxtFld.setText("");
		parent.getParent().getTablePanel().clearRowFilters();
	}

	@Override
	protected void search() {
		super.search();
		ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
		if (!sifraTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + sifraTxtFld.getText(), 0));
		}
		if (!lekarTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + lekarTxtFld.getText(), 1));
		}
		if (!jmbgTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + jmbgTxtFld.getText(), 2));
		}
		if (!lekTxtFld.getText().isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + lekTxtFld.getText(), 4));
		}
		parent.getParent().getTablePanel().filterRows(filters);
	}

}
