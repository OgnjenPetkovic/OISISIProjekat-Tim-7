package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.collections.Korisnici;
import model.collections.Lekovi;
import model.entity.Korisnik;

public class Podaci {
		
	private static Podaci instance = null;
	
	private ObjectMapper om;
	
	private Korisnici korisnici;
	private Lekovi lekovi;
	
	private Korisnik loggedInUser; 

	private Podaci() {
		om = new ObjectMapper();
		korisnici = new Korisnici();
		lekovi = new Lekovi();
	}
	
	public static Podaci getInstance() {
		if (instance == null) {
			instance = new Podaci();
		}
		
		return instance;
	}
	
	public void loadKorisnici() {
		korisnici.load(om);
	}
	
	public void loadData() {
		lekovi.load(om);
	}
	
	public void saveData() {
		korisnici.save(om);
		lekovi.save(om);
	}

	public Lekovi getLekovi() {
		return lekovi;
	}

	public void setLekovi(Lekovi lekovi) {
		this.lekovi = lekovi;
	}

	public Korisnici getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}

	public Korisnik getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Korisnik loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
}
