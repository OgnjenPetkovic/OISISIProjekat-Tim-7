package model.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import model.Podaci;
import model.entity.Korisnik;

public class KorisnikDeserializer extends JsonDeserializer<Korisnik> {

	@Override
	public Korisnik deserialize(JsonParser jpar, DeserializationContext dc)
			throws IOException, JsonProcessingException {
		String korisnickoIme = jpar.getValueAsString();
		return Podaci.getInstance().getKorisnici().findByKorIme(korisnickoIme);
	}

}
