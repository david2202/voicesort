package au.com.auspost.voicesort.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Geo {
    @Getter @Setter
    private double longitude;

    @Getter @Setter
    private double latitude;

    public Geo() {
        // Needed for Hibernate
    }

    public Geo(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude.doubleValue();
        this.longitude = longitude.doubleValue();
    }
}
