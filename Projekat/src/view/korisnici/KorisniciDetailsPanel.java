package view.korisnici;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import gui.MainWindow;
import model.Podaci;
import model.entity.Korisnik;
import model.util.TipKorisnika;
import view.AbstractDetailsPanel;
import view.util.DetailsFormState;
import view.util.FormLabel;
import view.util.FormTextField;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;

@SuppressWarnings("serial")
public class KorisniciDetailsPanel extends AbstractDetailsPanel {
	
	private FormLabel korImeLbl;
	private FormLabel lozinkaLbl;
	private FormLabel imeLbl;
	private FormLabel prezimeLbl;
	private FormLabel tipLbl;
	private FormTextField korImeTxtFld;
	private FormTextField lozinkaTxtFld;
	private FormTextField imeTxtFld;
	private FormTextField prezimeTxtFld;
	private JComboBox<TipKorisnika> tipCbx;
	
	
	public KorisniciDetailsPanel(KorisniciContentPanel parent) {
		super(parent);
		initGui();
		
		gBagC.gridx = 0;
		gBagC.gridy = 0;
		korImeLbl = new FormLabel("Korisničko ime");
		add(korImeLbl, gBagC);
		gBagC.gridx+=2;
		korImeTxtFld = new FormTextField();
		add(korImeTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		lozinkaLbl = new FormLabel("Lozinka");
		add(lozinkaLbl, gBagC);
		gBagC.gridx+=2;
		lozinkaTxtFld = new FormTextField();
		add(lozinkaTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
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
		TipKorisnika[] items = {TipKorisnika.APOTEKAR, TipKorisnika.LEKAR, TipKorisnika.ADMIN};
		tipCbx = new JComboBox<>(items);
		add(tipCbx, gBagC);
		
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
		korImeTxtFld.setText("");
		lozinkaTxtFld.setText("");
		imeTxtFld.setText("");
		tipCbx.setSelectedItem("Da");
		prezimeTxtFld.setText("");
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
			Podaci.getInstance().getKorisnici().removeKorisnik(korImeTxtFld.getText());
			super.delete();
		} catch (RecordDoesNotExistException e) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), 
					"Korisnik pod tim korisničkim imenom ne postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	@Override
	protected void confirm() {
		if (DetailsFormState.ADD.equals(state)) {
			try {
				Podaci.getInstance().getKorisnici().addNewKorisnik(korImeTxtFld.getText(),
						lozinkaTxtFld.getText(),
						imeTxtFld.getText(),
						prezimeTxtFld.getText(),
						(TipKorisnika)tipCbx.getSelectedItem());
			} catch (RecordAlreadyExistsException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), 
						"Korisnik pod tim korisničkim imenom već postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (DetailsFormState.EDIT.equals(state)) {
			try {
				Podaci.getInstance().getKorisnici().editKorisnik(korImeTxtFld.getText(),
						lozinkaTxtFld.getText(),
						imeTxtFld.getText(),
						prezimeTxtFld.getText(),
						(TipKorisnika)tipCbx.getSelectedItem());
			} catch (RecordDoesNotExistException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), 
						"Korisnik pod tim korisničkim imenom ne postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
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
				korImeTxtFld.setEditable(false);
				lozinkaTxtFld.setEditable(false);
				imeTxtFld.setEditable(false);
				tipCbx.setEnabled(false);
				prezimeTxtFld.setEditable(false);
				break;
			case ADD:
				korImeTxtFld.setEditable(true);
			case EDIT:
				lozinkaTxtFld.setEditable(true);
				imeTxtFld.setEditable(true);
				tipCbx.setEnabled(true);
				prezimeTxtFld.setEditable(true);
		}
	}
	
	public void insertTableData(Object rowData) {
		if (rowData instanceof Korisnik) {
			korImeTxtFld.setText(((Korisnik)rowData).getKorisnickoIme());
        	lozinkaTxtFld.setText(((Korisnik)rowData).getLozinka());
        	imeTxtFld.setText(((Korisnik)rowData).getIme());
        	prezimeTxtFld.setText(((Korisnik)rowData).getPrezime());
        	tipCbx.setSelectedItem(((Korisnik)rowData).getTip().getOpis());
		}
	}

	public FormTextField getSifraTxtFld() {
		return korImeTxtFld;
	}


	public FormTextField getNazivTxtFld() {
		return lozinkaTxtFld;
	}


	public FormTextField getProizvTxtFld() {
		return imeTxtFld;
	}


	public FormTextField getCenaTxtFld() {
		return prezimeTxtFld;
	}


	public JComboBox<TipKorisnika> getNaReceptCbx() {
		return tipCbx;
	}

}
