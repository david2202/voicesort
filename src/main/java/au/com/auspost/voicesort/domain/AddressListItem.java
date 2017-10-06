package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AddressListItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name="address_list_id")
    @JsonIgnore
    @Getter @Setter
    private AddressList addressList;

    @Getter @Setter
    private String text;

    @Getter @Setter
    private Integer dpid;

    @Getter @Setter
    private Integer deliveryOfficeRoundId;

    @Getter @Setter
    private int section;

    @Getter @Setter
    private int dpidSequenceNumber;

    @Embedded
    @Getter @Setter
    private Geo geo;

    public AddressListItem() {

    }
}
