package dev.myrold.lotty.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.f4b6a3.tsid.Tsid;

public class LottyJacksonModule extends SimpleModule {

    public LottyJacksonModule() {
        addSerializer(new TsidSerializer());
        addDeserializer(Tsid.class, new TsidDeserializer());
    }

}
