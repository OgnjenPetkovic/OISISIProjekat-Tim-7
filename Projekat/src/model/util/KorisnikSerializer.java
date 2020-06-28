package model.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import model.entity.Korisnik;

public class KorisnikSerializer extends JsonSerializer<Korisnik> {

	@Override
	public void serialize(Korisnik korisnik, JsonGenerator jgen, SerializerProvider sp) throws IOException {
		jgen.writeString(korisnik.getKorisnickoIme());
	}

}
