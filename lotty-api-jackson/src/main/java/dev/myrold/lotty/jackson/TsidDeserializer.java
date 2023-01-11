package dev.myrold.lotty.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.github.f4b6a3.tsid.Tsid;

import java.io.IOException;

public class TsidDeserializer extends StdDeserializer<Tsid> {

    private final StringDeserializer stringDeserializer = new StringDeserializer();

    public TsidDeserializer() {
        super(Tsid.class);
    }

    @Override public Tsid deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Tsid.from(stringDeserializer.deserialize(p, ctxt));
    }
}
