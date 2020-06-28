package model.collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.entity.Korisnik;
import model.util.TipKorisnika;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;

public class Korisnici implements ITableData {
	
	private ArrayList<Korisnik> data;
	
	public ArrayList<Korisnik> getData() {
		return data;
	}

	public void setData(ArrayList<Korisnik> data) {
		this.data = data;
	}

	@Override
	@JsonIgnore
	public String[] getColumnNames() {
		String[] columnNames = { "Korisničko ime",
	            "Lozinka",
	            "Ime",
	            "Prezime",
	            "Tip",
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
			case 0: return data.get(rowIndex).getKorisnickoIme();
			case 1: return data.get(rowIndex).getLozinka();
			case 2: return data.get(rowIndex).getIme();
			case 3: return data.get(rowIndex).getPrezime();
			case 4: return data.get(rowIndex).getTip();
			case 5: return (data.get(rowIndex).isObrisan()?"Da":"Ne");
			default: return "";
		}
	}

	@Override
	@JsonIgnore
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 4: return TipKorisnika.class;
			default: return String.class;
		}
	}
	
	public void addNewKorisnik(String korIme, String lozinka, String ime, String prezime, TipKorisnika tip) throws RecordAlreadyExistsException {
		if (findByKorImeAndLozinka(korIme, lozinka) != null) {
			throw new RecordAlreadyExistsException("Korisnik pod tim korisničkim imenom već postoji!");
		} else {
			data.add(new Korisnik(korIme, lozinka, ime, prezime, tip, false));
		}
	}
	
	public void editKorisnik(String korIme, String lozinka, String ime, String prezime) throws RecordDoesNotExistException {
		Korisnik existingKorisnik = findByKorIme(korIme);
		if (existingKorisnik == null) {
			throw new RecordDoesNotExistException("Korisnik pod tim korisničkim imenom ne postoji!");
		} else {
			existingKorisnik.setKorisnickoIme(korIme);
			existingKorisnik.setLozinka(lozinka);
			existingKorisnik.setIme(ime);
			existingKorisnik.setPrezime(prezime);
		}
	}
	
	public void removeKorisnik(String korIme) throws RecordDoesNotExistException {
		Korisnik existingKorisnik = findByKorIme(korIme);
		if (existingKorisnik == null) {
			throw new RecordDoesNotExistException("Korisnik pod tim korisničkim imenom ne postoji!");
		} else {
			existingKorisnik.setObrisan(true);
		}
	}
	
	public Korisnik findByKorIme(String korisnickoIme) {
		if (korisnickoIme == null || korisnickoIme.isEmpty()) {
			return null;
		}
		
		for (Korisnik korisnik : data) {
			if (korisnickoIme.equalsIgnoreCase(korisnik.getKorisnickoIme())) {
				return korisnik;
			}
		}
		return null;
	}
	
	public Korisnik findByKorImeAndLozinka(String korisnickoIme, String lozinka) {
		if (korisnickoIme == null || korisnickoIme.isEmpty() || lozinka == null || lozinka.isEmpty()) {
			return null;
		}
		
		for (Korisnik korisnik : data) {
			if (korisnickoIme.equalsIgnoreCase(korisnik.getKorisnickoIme()) && 
					lozinka.equalsIgnoreCase(korisnik.getLozinka())) {
				return korisnik;
			}
		}
		return null;
	}
	
	public void load(ObjectMapper om) {
		try {
			data = om.readValue(new File("podaci/korisnici.json"), new TypeReference<ArrayList<Korisnik>>() {});
		} catch (IOException e) {
			System.out.println("Korisnici - error during data loading: " + e.getMessage());
			data = new ArrayList<Korisnik>();
		}
	}
	
	public void save(ObjectMapper om) {
		try {
			om.writeValue(new File("podaci/korisnici.json"), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
