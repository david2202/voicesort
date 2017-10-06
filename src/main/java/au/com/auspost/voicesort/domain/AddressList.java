package au.com.auspost.voicesort.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AddressList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @OneToMany(mappedBy = "addressList", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<AddressListItem> addressListItems = new ArrayList<>();

    @Getter @Setter
    private Integer postCode;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Status statusCd;

    public enum Status {
        OPEN,
        CLOSED
    }

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Type type;

    public enum Type {
        LARGE_LETTER,
        SMALL_PARCEL
    }

    public AddressList() {

    }

    public void addAddressListItem(AddressListItem addressListItem) {
        if (addressListItems == null) {
            addressListItems = new ArrayList<>();
        }
        addressListItem.setAddressList(this);
        addressListItems.add(addressListItem);
    }

    public AddressList close() {
        this.setStatusCd(Status.CLOSED);
        return this;
    }
}
