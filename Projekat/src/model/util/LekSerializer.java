package model.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import model.entity.Lek;

public class LekSerializer extends JsonSerializer<Lek> {

	@Override
	public void serialize(Lek lek, JsonGenerator jgen, SerializerProvider sp) throws IOException {
		jgen.writeString(lek.getSifra());
	}

}
