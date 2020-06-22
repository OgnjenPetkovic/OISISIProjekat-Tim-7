package view.lekovi;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import gui.MainWindow;
import model.Podaci;
import model.entity.Lek;
import view.AbstractDetailsPanel;
import view.util.DetailsFormState;
import view.util.FormLabel;
import view.util.FormTextField;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;

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
	
	@Override
	protected void add() {
		super.add();
	}

	@Override
	protected void edit() {
		super.edit();
	}

	@Override
	protected void delete() {
		try {
			Podaci.getInstance().getLekovi().removeLek(sifraTxtFld.getText());
			super.delete();
		} catch (RecordDoesNotExistException e) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), 
					"Lek pod tom šifrom ne postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	@Override
	protected void confirm() {
		String naReceptValue = (String) naReceptCbx.getSelectedItem();
		if (DetailsFormState.ADD.equals(state)) {
			try {
				Podaci.getInstance().getLekovi().addNewLek(sifraTxtFld.getText(),
						nazivTxtFld.getText(),
						proizvTxtFld.getText(),
						naReceptValue.equalsIgnoreCase("Da")?true:false,
						Float.parseFloat(cenaTxtFld.getText()));
			} catch (RecordAlreadyExistsException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), 
						"Lek pod tom šifrom već postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (DetailsFormState.EDIT.equals(state)) {
			try {
				Podaci.getInstance().getLekovi().editLek(sifraTxtFld.getText(),
						nazivTxtFld.getText(),
						proizvTxtFld.getText(),
						naReceptValue.equalsIgnoreCase("Da")?true:false,
						Float.parseFloat(cenaTxtFld.getText()));
			} catch (RecordDoesNotExistException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), 
						"Lek pod tom šifrom ne postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		super.confirm();
	}

	@Override
	protected void cancel() {
		super.cancel();
	}
	
	@Override
	protected void changeFormState(DetailsFormState newState) {
		super.changeFormState(newState);
		switch (newState) {
			case DETAILS:
				sifraTxtFld.setEditable(false);
				nazivTxtFld.setEditable(false);
				proizvTxtFld.setEditable(false);
				naReceptCbx.setEnabled(false);
				cenaTxtFld.setEditable(false);
				break;
			case ADD:
				sifraTxtFld.setEditable(true);
			case EDIT:
				nazivTxtFld.setEditable(true);
				proizvTxtFld.setEditable(true);
				naReceptCbx.setEnabled(true);
				cenaTxtFld.setEditable(true);
		}
	}
	
	public void insertTableData(Object rowData) {
		if (rowData instanceof Lek) {
			sifraTxtFld.setText(((Lek)rowData).getSifra());
        	nazivTxtFld.setText(((Lek)rowData).getNaziv());
        	proizvTxtFld.setText(((Lek)rowData).getProizvodjac());
        	cenaTxtFld.setText(Float.toString(((Lek)rowData).getCena()));
        	naReceptCbx.setSelectedItem(((Lek)rowData).isNaRecept()?"Da":"Ne");
		}
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
