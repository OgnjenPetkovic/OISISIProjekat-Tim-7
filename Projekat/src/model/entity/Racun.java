package model.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import model.util.KorisnikDeserializer;
import model.util.KorisnikSerializer;

public class Racun {
	
	private Korisnik apotekar;
	private ArrayList<KorpaItem> items;
	
	public Racun() {}

	public Racun(Korisnik apotekar, ArrayList<KorpaItem> items) {
		this.apotekar = apotekar;
		this.items = items;
	}

	@JsonSerialize(using = KorisnikSerializer.class)
	public Korisnik getApotekar() {
		return apotekar;
	}

	@JsonDeserialize(using = KorisnikDeserializer.class)
	public void setApotekar(Korisnik apotekar) {
		this.apotekar = apotekar;
	}

	public ArrayList<KorpaItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<KorpaItem> items) {
		this.items = items;
	}

}
