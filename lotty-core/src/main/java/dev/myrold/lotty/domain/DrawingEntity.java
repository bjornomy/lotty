package dev.myrold.lotty.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import dev.myrold.lotty.domain.base.BaseEntity;
import dev.myrold.lotty.domain.base.LottyBaseEntity;
import dev.myrold.lotty.domain.base.TsidBaseEntityAware;
import dev.myrold.lotty.domain.hibernate.generator.TsidGenerator;
import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;


@Data
@Entity
@Introspected
@EqualsAndHashCode(callSuper = true, exclude = {"version"})
public class DrawingEntity extends LottyBaseEntity implements BaseEntity<Tsid>, TsidBaseEntityAware {

    private Tsid lotteryId;

    @Setter(AccessLevel.PRIVATE)
    @JoinColumn(name = "drawing_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PriceEntity> prices = new HashSet<>();

    private Instant drawnAt;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TsidGenerator.GENERATOR_NAME)
    @GenericGenerator(name = TsidGenerator.GENERATOR_NAME, strategy = TsidGenerator.GENERATOR_FQN)
    private Tsid id;

    @Version
    private Integer version;


    public boolean addPrice(PriceEntity price) {
        if (price == null) {
            return false;
        }

        price.setDrawingId(this.getId());
        return this.prices.add(price);
    }
    public void addPrices(Set<PriceEntity> prices) {
        prices.forEach(this::addPrice);
    }

}
