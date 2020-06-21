package view.lekovi;

import javax.swing.JComboBox;

import view.AbstractDetailsPanel;
import view.util.DetailsFormState;
import view.util.FormLabel;
import view.util.FormTextField;

@SuppressWarnings("serial")
public class LekoviDetailsPanel extends AbstractDetailsPanel {
	
	private FormLabel sifraLbl;
	private FormLabel nazivLbl;
	private FormLabel proizvLbl;
	private FormLabel cenaLbl;
	private FormLabel naReceptLbl;
	private FormTextField sifraTxtFld;
	private FormTextField nazivTxtFld;
	private FormTextField proizvTxtFld;
	private FormTextField cenaTxtFld;
	private JComboBox<String> naReceptCbx;
	
	
	public LekoviDetailsPanel(LekoviContentPanel parent) {
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
		naReceptLbl = new FormLabel("Na recept");
		add(naReceptLbl, gBagC);
		gBagC.gridx+=2;
		String[] items = {"Da", "Ne"};
		naReceptCbx = new JComboBox<>(items);
		add(naReceptCbx, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		cenaLbl = new FormLabel("Cena");
		add(cenaLbl, gBagC);
		gBagC.gridx+=2;
		cenaTxtFld = new FormTextField();
		add(cenaTxtFld, gBagC);
		
		gBagC.gridy++;
		gBagC.gridx = 0;
		add(add, gBagC);
		add(confirm, gBagC);
		gBagC.gridx++;
		add(edit, gBagC);
		gBagC.gridx++;
		add(delete, gBagC);
		add(cancel, gBagC);

		changeFormState(DetailsFormState.DETAILS);
	}
	
	@Override
	protected void clearFields() {
		sifraTxtFld.setText("");
		nazivTxtFld.setText("");
		proizvTxtFld.setText("");
		naReceptCbx.setSelectedItem("Da");
		cenaTxtFld.setText("");
	}

	public FormTextField getSifraTxtFld() {
		return sifraTxtFld;
	}


	public FormTextField getNazivTxtFld() {
		return nazivTxtFld;
	}


	public FormTextField getProizvTxtFld() {
		return proizvTxtFld;
	}


	public FormTextField getCenaTxtFld() {
		return cenaTxtFld;
	}


	public JComboBox<String> getNaReceptCbx() {
		return naReceptCbx;
	}

}
