package dev.myrold.lotty.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.github.f4b6a3.tsid.Tsid;

import java.io.IOException;

public class TsidSerializer extends StdSerializer<Tsid> {

    private final StringSerializer stringSerializer = new StringSerializer();

    public TsidSerializer() {
        super(Tsid.class);
    }

    @Override
    public void serialize(Tsid value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        stringSerializer.serialize(value.toString(), gen, provider);
    }

}
