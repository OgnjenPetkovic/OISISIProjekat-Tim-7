package model.entity;

public class Lek {
	private String sifra;
	private String naziv;
	private String proizvodjac;
	private boolean naRecept;
	private float cena;
	private boolean obrisan;
	
	public Lek() {}
	
	public Lek(String sifra, String naziv, String proizvodjac, boolean naRecept, float cena, boolean obrisan) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.proizvodjac = proizvodjac;
		this.naRecept = naRecept;
		this.cena = cena;
		this.obrisan = obrisan;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public boolean isNaRecept() {
		return naRecept;
	}

	public void setNaRecept(boolean naRecept) {
		this.naRecept = naRecept;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
}
