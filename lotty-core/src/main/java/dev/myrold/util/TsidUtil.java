package dev.myrold.util;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidFactory;

import java.util.concurrent.ThreadLocalRandom;

public class TsidUtil {

    private static final TsidFactory INSTANCE = TsidFactory.builder()
        .withRandom(ThreadLocalRandom.current())
        .build();


    public static Long next() {
        return INSTANCE.create().toLong();
    }

    public static String longToString(Long tsid) {
        return Tsid.from(tsid).toString();
    }

    public static Long stringToLong(String tsid) {
        return Tsid.from(tsid).toLong();
    }
}
