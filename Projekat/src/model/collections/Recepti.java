package model.collections;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Podaci;
import model.entity.Korisnik;
import model.entity.Lek;
import model.entity.Recept;
import model.util.TipKorisnika;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;
import view.util.exceptions.RelatedRecordNotFound;

public class Recepti implements ITableData {
	
	private ArrayList<Recept> data;
	
	public ArrayList<Recept> getData() {
		return data;
	}

	public void setData(ArrayList<Recept> data) {
		this.data = data;
	}

	@Override
	@JsonIgnore
	public String[] getColumnNames() {
		String[] columnNames = { "Šifra",
	            "Lekar",
	            "JMBG pacijenta",
	            "Datum i vreme",
	            "Šifra leka",
	            "Količina",
	            "Ukupna cena",
	            "Obrisan"};
		return columnNames;
	}

	@Override
	@JsonIgnore
	public int getRowCount() {
		return data.size();
	}

	@Override
	@JsonIgnore
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0: return data.get(rowIndex).getSifra();
			case 1: return data.get(rowIndex).getLekar().getIme() + " " + data.get(rowIndex).getLekar().getPrezime();
			case 2: return data.get(rowIndex).getJmbg();
			case 3: return data.get(rowIndex).getDatumIVreme();
			case 4: return data.get(rowIndex).getLek().getSifra();
			case 5: return data.get(rowIndex).getKolicina();
			case 6: return data.get(rowIndex).getUkupnaCena();
			case 7: return (data.get(rowIndex).isObrisan()?"Da":"Ne");
			default: return "";
		}
	}

	@Override
	@JsonIgnore
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0:
			case 5: return Integer.class;
			case 3: return Date.class;
			case 6: return Float.class;
			default: return String.class;
		}
	}
	
	public void addNewRecept(int sifra, String lekarKorIme, String jmbg, String datumIVremeString, String sifraLeka, int kolicina, float ukupnaCena) throws RecordAlreadyExistsException, RelatedRecordNotFound, ParseException {
		if (findBySifra(sifra) != null) {
			throw new RecordAlreadyExistsException("Recept pod tom šifrom već postoji!");
		} else {
			Korisnik lekar = Podaci.getInstance().getKorisnici().findByKorIme(lekarKorIme);
			if (lekar == null || !TipKorisnika.LEKAR.equals(lekar.getTip()) || lekar.isObrisan()) {
				throw new RelatedRecordNotFound("Izabrani lekar ne postoji!");
			}
			Lek lek = Podaci.getInstance().getLekovi().findBySifra(sifraLeka);
			if (lek == null || lek.isObrisan()) {
				throw new RelatedRecordNotFound("Izabrani lek ne postoji!");
			}
			data.add(new Recept(sifra, lekar, jmbg, Podaci.getInstance().getSdf().parse(datumIVremeString), lek, kolicina, ukupnaCena, false));
		}
	}
	
	public void editRecept(int sifra, String lekarKorIme, String jmbg, String datumIVremeString, String sifraLeka, int kolicina, float ukupnaCena) throws RecordDoesNotExistException, RelatedRecordNotFound, ParseException {
		Recept existingRecept = findBySifra(sifra);
		if (existingRecept == null) {
			throw new RecordDoesNotExistException("Recept pod tom šifrom ne postoji!");
		} else {
			Korisnik lekar = Podaci.getInstance().getKorisnici().findByKorIme(lekarKorIme);
			if (lekar == null || !TipKorisnika.LEKAR.equals(lekar.getTip()) || lekar.isObrisan()) {
				throw new RelatedRecordNotFound("Izabrani lekar ne postoji!");
			}
			Lek lek = Podaci.getInstance().getLekovi().findBySifra(sifraLeka);
			if (lek == null || lek.isObrisan()) {
				throw new RelatedRecordNotFound("Izabrani lek ne postoji!");
			}
			existingRecept.setLekar(lekar);
			existingRecept.setJmbg(jmbg);
			existingRecept.setDatumIVreme(Podaci.getInstance().getSdf().parse(datumIVremeString));
			existingRecept.setLek(lek);
			existingRecept.setKolicina(kolicina);
			existingRecept.setUkupnaCena(ukupnaCena);
		}
	}
	
	public void removeRecept(int sifra) throws RecordDoesNotExistException {
		Recept existingRecept = findBySifra(sifra);
		if (existingRecept == null) {
			throw new RecordDoesNotExistException("Recept pod tom šifrom ne postoji!");
		} else {
			existingRecept.setObrisan(true);
		}
	}
	
	public Recept findBySifra(int sifra) {
		for (Recept recept : data) {
			if (sifra == recept.getSifra()) {
				return recept;
			}
		}
		return null;
	}
	
	public Recept findBySifraLeka(String sifraLeka) {
		for (Recept recept : data) {
			if (sifraLeka.equals(recept.getLek().getSifra()) && !recept.isObrisan()) {
				return recept;
			}
		}
		return null;
	}
	
	public void load(ObjectMapper om) {
		try {
			data = om.readValue(new File("podaci/recepti.json"), new TypeReference<ArrayList<Recept>>() {});
		} catch (IOException e) {
			System.out.println("Recepti - error during data loading: " + e.getMessage());
			data = new ArrayList<Recept>();
		}
	}
	
	public void save(ObjectMapper om) {
		try {
			om.writeValue(new File("podaci/recepti.json"), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
