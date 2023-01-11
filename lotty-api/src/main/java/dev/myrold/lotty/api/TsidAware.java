package dev.myrold.lotty.api;

import com.github.f4b6a3.tsid.Tsid;

public interface TsidAware {

    String id();

    default Tsid idAsTsid() {
        return Tsid.from(id());
    }

}
