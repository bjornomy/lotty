package dev.myrold.lotty.domain.hibernate.type;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.BigIntTypeDescriptor;

public class TsidType extends AbstractSingleColumnStandardBasicType<Tsid> {

    public static final String TSID_TYPE = "Tsid";

    public static final TsidType INSTANCE = new TsidType();

    public TsidType() {
        super(BigIntTypeDescriptor.INSTANCE, TsidTypeJavaDescriptor.INSTANCE);
    }

    @Override public String getName() {
        return this.getClass().getSimpleName();
    }

}
