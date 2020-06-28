package model.entity;

public class ReportItem {
	private String sifra;
	private String naziv;
	private String proizvodjac;
	private int kolicina;
	private float jedCena;
	
	public ReportItem() {}

	public ReportItem(String sifra, String naziv, String proizvodjac, int kolicina, float jedCena) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.proizvodjac = proizvodjac;
		this.kolicina = kolicina;
		this.jedCena = jedCena;
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

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public float getJedCena() {
		return jedCena;
	}

	public void setJedCena(float jedCena) {
		this.jedCena = jedCena;
	}
}
