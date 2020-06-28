package model.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import model.Podaci;
import model.entity.Lek;

public class LekDeserializer extends JsonDeserializer<Lek> {

	@Override
	public Lek deserialize(JsonParser jpar, DeserializationContext dc) throws IOException, JsonProcessingException {
		String sifra = jpar.getValueAsString();
		return Podaci.getInstance().getLekovi().findBySifra(sifra);
	}

}
