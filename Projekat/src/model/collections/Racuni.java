package model.collections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.entity.Korisnik;
import model.entity.KorpaItem;
import model.entity.Lek;
import model.entity.Racun;
import model.entity.ReportItem;

public class Racuni {
	
	private ArrayList<Racun> data;
	
	public ArrayList<Racun> getData() {
		return data;
	}

	public void setData(ArrayList<Racun> data) {
		this.data = data;
	}
	
	public ArrayList<ReportItem> generateReportData() {
		HashMap<String, ReportItem> tempMap = new HashMap<>();
		for (Racun racun : data) {
			for (KorpaItem ki : racun.getItems()) {
				String sifraLeka = ki.getLek().getSifra();
				if (tempMap.containsKey(sifraLeka)) {
					ReportItem existingRi = tempMap.get(sifraLeka);
					existingRi.setKolicina(existingRi.getKolicina() + ki.getKolicina());
				} else {
					Lek lek = ki.getLek();
					tempMap.put(ki.getLek().getSifra(), new ReportItem(lek.getSifra(), lek.getNaziv(), 
							lek.getProizvodjac(), ki.getKolicina(), lek.getCena()));
				}
			}
		}
		return new ArrayList<>(tempMap.values());
	}
	
	public ArrayList<ReportItem> generateReportData(Korisnik apotekar) {
		HashMap<String, ReportItem> tempMap = new HashMap<>();
		for (Racun racun : data) {
			if (racun.getApotekar().getKorisnickoIme().equals(apotekar.getKorisnickoIme())) {
				for (KorpaItem ki : racun.getItems()) {
					String sifraLeka = ki.getLek().getSifra();
					if (tempMap.containsKey(sifraLeka)) {
						ReportItem existingRi = tempMap.get(sifraLeka);
						existingRi.setKolicina(existingRi.getKolicina() + ki.getKolicina());
					} else {
						Lek lek = ki.getLek();
						tempMap.put(ki.getLek().getSifra(), new ReportItem(lek.getSifra(), lek.getNaziv(), 
								lek.getProizvodjac(), ki.getKolicina(), lek.getCena()));
					}
				}
			}
		}
		return new ArrayList<>(tempMap.values());
	}
	
	public ArrayList<ReportItem> generateReportData(String proizvodjac) {
		Pattern p = Pattern.compile("(?i)" + proizvodjac);
		HashMap<String, ReportItem> tempMap = new HashMap<>();
		for (Racun racun : data) {
			for (KorpaItem ki : racun.getItems()) {
				if (p.matcher(ki.getLek().getProizvodjac()).find()) {
					String sifraLeka = ki.getLek().getSifra();
					if (tempMap.containsKey(sifraLeka)) {
						ReportItem existingRi = tempMap.get(sifraLeka);
						existingRi.setKolicina(existingRi.getKolicina() + ki.getKolicina());
					} else {
						Lek lek = ki.getLek();
						tempMap.put(ki.getLek().getSifra(), new ReportItem(lek.getSifra(), lek.getNaziv(), 
								lek.getProizvodjac(), ki.getKolicina(), lek.getCena()));
					}
				}
			}
		}
		return new ArrayList<>(tempMap.values());
	}
	
	public void load(ObjectMapper om) {
		try {
			data = om.readValue(new File("podaci/racuni.json"), new TypeReference<ArrayList<Racun>>() {});
		} catch (IOException e) {
			System.out.println("Racuni - error during data loading: " + e.getMessage());
			data = new ArrayList<Racun>();
		}
	}
	
	public void save(ObjectMapper om) {
		try {
			om.writeValue(new File("podaci/racuni.json"), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
