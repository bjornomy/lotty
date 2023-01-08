package dev.myrold.config;

import com.github.f4b6a3.tsid.TsidFactory;

import java.util.concurrent.ThreadLocalRandom;

public class TsidConfig {

    public static final TsidFactory INSTANCE = TsidFactory.builder()
        .withRandom(ThreadLocalRandom.current())
        .build();

}
