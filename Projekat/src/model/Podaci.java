package model;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.collections.Korisnici;
import model.collections.Korpa;
import model.collections.Lekovi;
import model.collections.Racuni;
import model.collections.Recepti;
import model.entity.Korisnik;

public class Podaci {
		
	private static Podaci instance = null;
	
	private ObjectMapper om;
	
	private Korisnici korisnici;
	private Lekovi lekovi;
	private Recepti recepti;
	private Racuni racuni;
	
	private Korpa korpa;
	
	private Korisnik loggedInUser;
	
	private SimpleDateFormat sdf;

	private Podaci() {
		om = new ObjectMapper();
		korisnici = new Korisnici();
		lekovi = new Lekovi();
		recepti = new Recepti();
		racuni = new Racuni();
		korpa = new Korpa();
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
		recepti.load(om);
		racuni.load(om);
	}
	
	public void saveData() {
		korisnici.save(om);
		lekovi.save(om);
		recepti.save(om);
		racuni.save(om);
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
	
	public Recepti getRecepti() {
		return recepti;
	}

	public void setRecepti(Recepti recepti) {
		this.recepti = recepti;
	}
	
	public Racuni getRacuni() {
		return racuni;
	}

	public void setRacuni(Racuni racuni) {
		this.racuni = racuni;
	}
	
	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Korisnik getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Korisnik loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
}
