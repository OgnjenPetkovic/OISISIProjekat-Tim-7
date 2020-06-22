package view.lekovi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;

import view.AbstractSearchPanel;
import view.util.FormLabel;
import view.util.FormTextField;

@SuppressWarnings("serial")
public class LekoviSearchPanel extends AbstractSearchPanel {
	
	private FormLabel sifraLbl;
	private FormLabel nazivLbl;
	private FormLabel proizvLbl;
	private FormLabel opsegCenaLbl;
	private FormTextField sifraTxtFld;
	private FormTextField nazivTxtFld;
	private FormTextField proizvTxtFld;
	private JComboBox<String> opsegCenaCbx;
	
	public LekoviSearchPanel(LekoviContentPanel parent) {
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
		nazivLbl = new FormLabel("Naziv");
		add(nazivLbl, gBagC);
		gBagC.gridx+=2;
		nazivTxtFld = new FormTextField();
		add(nazivTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		proizvLbl = new FormLabel("Proizvođač");
		add(proizvLbl, gBagC);
		gBagC.gridx+=2;
		proizvTxtFld = new FormTextField();
		add(proizvTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		opsegCenaLbl = new FormLabel("Opseg cene");
		add(opsegCenaLbl, gBagC);
		gBagC.gridx+=2;
		String[] items = {"", "0-1000.00", "1000.00+"};
		opsegCenaCbx = new JComboBox<>(items);
		add(opsegCenaCbx, gBagC);
		
		gBagC.gridy++;
		gBagC.gridx = 0;
		add(search, gBagC);
		gBagC.gridx+=2;
		add(clear, gBagC);
		
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
				if (!sifraTxtFld.getText().isEmpty()) {
					filters.add(RowFilter.regexFilter("(?i)" + sifraTxtFld.getText(), 0));
				}
				if (!nazivTxtFld.getText().isEmpty()) {
					filters.add(RowFilter.regexFilter("(?i)" + nazivTxtFld.getText(), 1));
				}
				if (!proizvTxtFld.getText().isEmpty()) {
					filters.add(RowFilter.regexFilter("(?i)" + proizvTxtFld.getText(), 2));
				}
				if (!((String)opsegCenaCbx.getSelectedItem()).isEmpty()) {
					switch ((String) opsegCenaCbx.getSelectedItem()) {
						case "0-1000.00": {
							filters.add(RowFilter.numberFilter(ComparisonType.BEFORE, 1000.0f, 4));
							break;
						}
						case "1000.00+": {
							filters.add(RowFilter.numberFilter(ComparisonType.AFTER, 1000.0f, 4));
							break;
						}
					}
				}
				parent.getParent().getTablePanel().filterRows(filters);
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sifraTxtFld.setText("");
				nazivTxtFld.setText("");
				proizvTxtFld.setText("");
				opsegCenaCbx.setSelectedItem("");
				parent.getParent().getTablePanel().clearRowFilters();
			}
		});
	}
}
