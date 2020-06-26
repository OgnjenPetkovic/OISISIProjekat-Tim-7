package view.recepti;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.Date;

import model.Podaci;
import model.entity.Korisnik;
import model.entity.Lek;
import model.entity.Recept;
import model.util.TipKorisnika;
import view.AbstractDetailsPanel;
import view.util.DetailsFormState;
import view.util.FormLabel;
import view.util.FormTextField;
import view.util.Utility;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;
import view.util.exceptions.RelatedRecordNotFound;
import view.util.filters.CustomIntegerFilter;
import view.util.filters.CustomJMBGFilter;

@SuppressWarnings("serial")
public class ReceptiDetailsPanel extends AbstractDetailsPanel {
	
	private FormLabel sifraLbl;
	private FormLabel lekarLbl;
	private FormLabel jmbgLbl;
	private FormLabel datumIVremeLbl;
	private FormLabel lekLbl;
	private FormLabel kolicinaLbl;
	private FormLabel ukupnaCenaLbl;
	private FormTextField sifraTxtFld;
	private FormTextField lekarTxtFld;
	private FormTextField jmbgTxtFld;
	private FormTextField datumIVremeTxtFld;
	private FormTextField lekTxtFld;
	private FormTextField kolicinaTxtFld;
	private FormTextField ukupnaCenaTxtFld;

	public ReceptiDetailsPanel(ReceptiContentPanel parent, boolean readonly) {
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
		jmbgTxtFld = new FormTextField(new CustomJMBGFilter());
		add(jmbgTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		datumIVremeLbl = new FormLabel("Datum i vreme");
		add(datumIVremeLbl, gBagC);
		gBagC.gridx+=2;
		datumIVremeTxtFld = new FormTextField();
		add(datumIVremeTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		lekLbl = new FormLabel("Šifra leka");
		add(lekLbl, gBagC);
		gBagC.gridx+=2;
		lekTxtFld = new FormTextField();
		lekTxtFld.addFocusListener(new ReceptiFocusListener());
		add(lekTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		kolicinaLbl = new FormLabel("Količina");
		add(kolicinaLbl, gBagC);
		gBagC.gridx+=2;
		kolicinaTxtFld = new FormTextField(new CustomIntegerFilter());
		kolicinaTxtFld.addFocusListener(new ReceptiFocusListener());
		add(kolicinaTxtFld, gBagC);
		
		gBagC.gridx = 0;
		gBagC.gridy++; 
		ukupnaCenaLbl = new FormLabel("Ukupna cena");
		add(ukupnaCenaLbl, gBagC);
		gBagC.gridx+=2;
		ukupnaCenaTxtFld = new FormTextField();
		add(ukupnaCenaTxtFld, gBagC);
		
		if (!readonly) {
			gBagC.gridy++;
			gBagC.gridx = 0;
			add(add, gBagC);
			add(confirm, gBagC);
			gBagC.gridx++;
			add(edit, gBagC);
			gBagC.gridx++;
			add(delete, gBagC);
			add(cancel, gBagC);
		}

		changeFormState(DetailsFormState.DETAILS);
	}
	
	@Override
	protected void clearFields() {
		sifraTxtFld.setText("");
		lekarTxtFld.setText("");
		jmbgTxtFld.setText("");
		datumIVremeTxtFld.setText("");
		lekTxtFld.setText("");
		kolicinaTxtFld.setText("");
		ukupnaCenaTxtFld.setText("");
	}
	
	@Override
	protected void add() {
		super.add();
		sifraTxtFld.setText(Integer.toString(Podaci.getInstance().getRecepti().getData().size()+1));
		datumIVremeTxtFld.setText(Podaci.getInstance().getSdf().format(new Date()));
		Korisnik loggedInUser = Podaci.getInstance().getLoggedInUser();
		if (TipKorisnika.LEKAR.equals(loggedInUser.getTip())) {
			lekarTxtFld.setText(loggedInUser.getKorisnickoIme());
		}
	}

	@Override
	protected void edit() {
		super.edit();
	}

	@Override
	protected void delete() {
		try {
			Podaci.getInstance().getRecepti().removeRecept(Integer.parseInt(sifraTxtFld.getText()));
			super.delete();
		} catch (RecordDoesNotExistException e) {
			Utility.showErrorMessage(e.getMessage());
			return;
		}
	}

	@Override
	protected void confirm() {
		if (DetailsFormState.ADD.equals(state)) {
			try {
				Podaci.getInstance().getRecepti().addNewRecept(Integer.parseInt(sifraTxtFld.getText()),
						lekarTxtFld.getText(), jmbgTxtFld.getText(),
						datumIVremeTxtFld.getText(),
						lekTxtFld.getText(), Integer.parseInt(kolicinaTxtFld.getText()),
						Float.parseFloat(ukupnaCenaTxtFld.getText()));
			} catch (RecordAlreadyExistsException | RelatedRecordNotFound e) {
				Utility.showErrorMessage(e.getMessage());
				return;
			} catch (ParseException e) {
				Utility.showErrorMessage("Datum mora biti u sledećem formatu: dan/mesec/godina sati:minute");
				return;
			}
		} else if (DetailsFormState.EDIT.equals(state)) {
			try {
				Podaci.getInstance().getRecepti().editRecept(Integer.parseInt(sifraTxtFld.getText()),
						lekarTxtFld.getText(), jmbgTxtFld.getText(),
						datumIVremeTxtFld.getText(),
						lekTxtFld.getText(), Integer.parseInt(kolicinaTxtFld.getText()),
						Float.parseFloat(ukupnaCenaTxtFld.getText()));
			} catch (RecordDoesNotExistException | RelatedRecordNotFound e) {
				Utility.showErrorMessage(e.getMessage());
				return;
			} catch (ParseException e) {
				Utility.showErrorMessage("Datum mora biti u sledećem formatu: dan/mesec/godina sati:minute");
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
				lekarTxtFld.setEditable(false);
				jmbgTxtFld.setEditable(false);
				datumIVremeTxtFld.setEditable(false);
				lekTxtFld.setEditable(false);
				kolicinaTxtFld.setEditable(false);
				ukupnaCenaTxtFld.setEditable(false);
				break;
			case ADD:
			case EDIT:
				lekarTxtFld.setEditable(true);
				jmbgTxtFld.setEditable(true);
				datumIVremeTxtFld.setEditable(true);
				lekTxtFld.setEditable(true);
				kolicinaTxtFld.setEditable(true);
		}
	}
	
	@Override
	protected int isFormValid() {
		if (sifraTxtFld.getText().isEmpty() || lekarTxtFld.getText().isEmpty() 
				|| jmbgTxtFld.getText().isEmpty() || datumIVremeTxtFld.getText().isEmpty()
				|| lekTxtFld.getText().isEmpty() || kolicinaTxtFld.getText().isEmpty()
				|| ukupnaCenaTxtFld.getText().isEmpty()) {
			return 0;
		}
		if (jmbgTxtFld.getText().length() < 13) {
			Utility.showErrorMessage("JMBG ne može imati manje od 13 cifara!");
			return -1;
		}
		return super.isFormValid();
	}
	
	@Override
	public void insertTableData(Object rowData) {
		if (rowData instanceof Recept) {
			sifraTxtFld.setText(Integer.toString((((Recept) rowData).getSifra())));
			lekarTxtFld.setText(((Recept) rowData).getLekar().getKorisnickoIme());
			jmbgTxtFld.setText(((Recept) rowData).getJmbg());
			datumIVremeTxtFld.setText(Podaci.getInstance().getSdf().format(((Recept) rowData).getDatumIVreme()));
			lekTxtFld.setText(((Recept) rowData).getLek().getSifra());
			kolicinaTxtFld.setText(Integer.toString(((Recept) rowData).getKolicina()));
			ukupnaCenaTxtFld.setText(Float.toString(((Recept) rowData).getUkupnaCena()));
		}
	}
	
	public void calculateUkupnaCena() {
		String sifraLeka = lekTxtFld.getText();
		String kolicina = kolicinaTxtFld.getText();
		if (sifraLeka.isEmpty() || kolicina.isEmpty()) {
			ukupnaCenaTxtFld.setText("0");
		} else {
			Lek lek = Podaci.getInstance().getLekovi().findBySifra(sifraLeka);
			if (lek == null || lek.isObrisan()) {
				ukupnaCenaTxtFld.setText("0");
			} else {
				ukupnaCenaTxtFld.setText(Float.toString(lek.getCena() * Integer.parseInt(kolicina)));
			}
		}
	}

	public FormTextField getSifraTxtFld() {
		return sifraTxtFld;
	}

	public void setSifraTxtFld(FormTextField sifraTxtFld) {
		this.sifraTxtFld = sifraTxtFld;
	}

	public FormTextField getLekarTxtFld() {
		return lekarTxtFld;
	}

	public void setLekarTxtFld(FormTextField lekarTxtFld) {
		this.lekarTxtFld = lekarTxtFld;
	}

	public FormTextField getJmbgTxtFld() {
		return jmbgTxtFld;
	}

	public void setJmbgTxtFld(FormTextField jmbgTxtFld) {
		this.jmbgTxtFld = jmbgTxtFld;
	}

	public FormTextField getDatumIVremeTxtFld() {
		return datumIVremeTxtFld;
	}

	public void setDatumIVremeTxtFld(FormTextField datumIVremeTxtFld) {
		this.datumIVremeTxtFld = datumIVremeTxtFld;
	}

	public FormTextField getLekTxtFld() {
		return lekTxtFld;
	}

	public void setLekTxtFld(FormTextField lekTxtFld) {
		this.lekTxtFld = lekTxtFld;
	}

	public FormTextField getKolicinaTxtFld() {
		return kolicinaTxtFld;
	}

	public void setKolicinaTxtFld(FormTextField kolicinaTxtFld) {
		this.kolicinaTxtFld = kolicinaTxtFld;
	}

	public FormTextField getUkupnaCenaTxtFld() {
		return ukupnaCenaTxtFld;
	}

	public void setUkupnaCenaTxtFld(FormTextField ukupnaCenaTxtFld) {
		this.ukupnaCenaTxtFld = ukupnaCenaTxtFld;
	}
	
	private class ReceptiFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			if (!DetailsFormState.DETAILS.equals(state)) {
				calculateUkupnaCena();
			}
		}
	}
	
}
