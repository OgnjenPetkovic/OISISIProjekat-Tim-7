package model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import model.util.LekDeserializer;
import model.util.LekSerializer;

public class KorpaItem {
	
	private Lek lek;
	private int kolicina;
	
	public KorpaItem() {}

	public KorpaItem(Lek lek, int kolicina) {
		this.lek = lek;
		this.kolicina = kolicina;
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

}
