package dev.myrold.lotty.domain.hibernate.generator;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import dev.myrold.lotty.util.UserUtil;

public class UserGenerator implements ValueGenerator<String> {

    @Override
    public String generateValue(Session session, Object owner) {
        return UserUtil.getUserIdentityFromRequestContext();
    }

}
