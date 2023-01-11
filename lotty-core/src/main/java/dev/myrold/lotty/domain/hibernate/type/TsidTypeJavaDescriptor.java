package dev.myrold.lotty.domain.hibernate.type;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

public class TsidTypeJavaDescriptor extends AbstractTypeDescriptor<Tsid> {

    public static final TsidTypeJavaDescriptor INSTANCE = new TsidTypeJavaDescriptor();

    public TsidTypeJavaDescriptor() {
        super(Tsid.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override public Tsid fromString(String string) {
        if (string == null) {
            return null;
        }
        return Tsid.from(string);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(Tsid value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        } else if (Long.class.isAssignableFrom(type)) {
            return (X) Long.valueOf(value.toLong());
        } else if (Tsid.class.isAssignableFrom(type)) {
            return (X) value;
        }

        throw unknownUnwrap(type);
    }

    @Override
    public <X> Tsid wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (value instanceof String s) {
            return Tsid.from(s);
        } else if (value instanceof Long l) {
            return Tsid.from(l);
        }

        throw unknownWrap(value.getClass());
    }
}
