package view.lekovi;

import javax.swing.JComboBox;

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
		sifraLbl = new FormLabel("Å ifra");
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
		proizvLbl = new FormLabel("ProizvoÄ‘aÄ�");
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
	}
}
