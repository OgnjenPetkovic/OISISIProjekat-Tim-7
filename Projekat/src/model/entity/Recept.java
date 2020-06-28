package model.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import model.util.LekDeserializer;
import model.util.LekSerializer;
import model.util.KorisnikDeserializer;
import model.util.KorisnikSerializer;

public class Recept {
	
	private int sifra;
	private Korisnik lekar;
	private String jmbg;
	private Date datumIVreme;
	private Lek lek;
	private int kolicina;
	private float ukupnaCena;
	private boolean obrisan;
	
	public Recept() {}

	public Recept(int sifra, Korisnik lekar, String jmbg, Date datumIVreme, Lek lek, int kolicina, float ukupnaCena, boolean obrisan) {
		this.sifra = sifra;
		this.lekar = lekar;
		this.jmbg = jmbg;
		this.datumIVreme = datumIVreme;
		this.lek = lek;
		this.kolicina = kolicina;
		this.ukupnaCena = ukupnaCena;
		this.obrisan = obrisan;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	@JsonSerialize(using = KorisnikSerializer.class)
	public Korisnik getLekar() {
		return lekar;
	}

	@JsonDeserialize(using = KorisnikDeserializer.class)
	public void setLekar(Korisnik lekar) {
		this.lekar = lekar;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Date getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(Date datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	@JsonSerialize(using = LekSerializer.class)
	public Lek getLek() {
		return lek;
	}

	@JsonDeserialize(using = LekDeserializer.class)
	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public float getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(float ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

}
