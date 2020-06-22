package model.collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.entity.Lek;
import view.util.exceptions.RecordAlreadyExistsException;
import view.util.exceptions.RecordDoesNotExistException;

public class Lekovi implements ITableData {
	
	private ArrayList<Lek> data;
	
	public ArrayList<Lek> getData() {
		return data;
	}

	public void setData(ArrayList<Lek> data) {
		this.data = data;
	}

	@Override
	@JsonIgnore
	public String[] getColumnNames() {
		String[] columnNames = { "Šifra",
	            "Naziv",
	            "Proizvođač",
	            "Na recept",
	            "Cena",
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
			case 1: return data.get(rowIndex).getNaziv();
			case 2: return data.get(rowIndex).getProizvodjac();
			case 3: return (data.get(rowIndex).isNaRecept()?"Da":"Ne");
			case 4: return data.get(rowIndex).getCena();
			case 5: return (data.get(rowIndex).isObrisan()?"Da":"Ne");
			default: return "";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 4: return Float.class;
			default: return String.class;
		}
	}
	
	public void addNewLek(String sifra, String naziv, String proizvodjac, boolean naRecept, float cena) throws RecordAlreadyExistsException {
		if (findBySifra(sifra) != null) {
			throw new RecordAlreadyExistsException();
		} else {
			data.add(new Lek(sifra, naziv, proizvodjac, naRecept, cena, false));
		}
	}
	
	public void editLek(String sifra, String naziv, String proizvodjac, boolean naRecept, float cena) throws RecordDoesNotExistException {
		Lek existingLek = findBySifra(sifra);
		if (existingLek == null) {
			throw new RecordDoesNotExistException();
		} else {
			existingLek.setNaziv(naziv);
			existingLek.setProizvodjac(proizvodjac);
			existingLek.setNaRecept(naRecept);
			existingLek.setCena(cena);
		}
	}
	
	public void removeLek(String sifra) throws RecordDoesNotExistException {
		Lek existingLek = findBySifra(sifra);
		if (existingLek == null) {
			throw new RecordDoesNotExistException();
		} else {
			existingLek.setObrisan(true);
		}
	}

	public Lek findBySifra(String sifra) {
		if (sifra == null || sifra.isEmpty()) {
			return null;
		}
		
		for (Lek lek : data) {
			if (sifra.equalsIgnoreCase(lek.getSifra())) {
				return lek;
			}
		}
		return null;
	}
	
	public void load(ObjectMapper om) {
		try {
			data = om.readValue(new File("podaci/lekovi.json"), new TypeReference<ArrayList<Lek>>() {});
		} catch (IOException e) {
			System.out.println("Lekovi - error during data loading: " + e.getMessage());
			data = new ArrayList<Lek>();
		}
	}
	
	public void save(ObjectMapper om) {
		try {
			om.writeValue(new File("podaci/lekovi.json"), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
