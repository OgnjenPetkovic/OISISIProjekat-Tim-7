package model.util;

public enum TipKorisnika {

	LEKAR("Lekar"), 
	APOTEKAR("Apotekar"),
	ADMIN("Administrator");
	
	private String opis;
	
	private TipKorisnika(String opis) {
		this.opis = opis;
	}
	
	public String getOpis() {
		return opis;
	}
	
}
