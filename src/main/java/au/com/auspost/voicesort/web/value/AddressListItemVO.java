package au.com.auspost.voicesort.web.value;

import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import au.com.auspost.voicesort.domain.Geo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

public class AddressListItemVO {
    @Getter @Setter
    private Integer id;

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

    @Getter @Setter
    private Geo geo;

    public AddressListItemVO(AddressListItem addressListItem) {
        id = addressListItem.getId();
        text = addressListItem.getText();
        dpid = addressListItem.getDpid();
        deliveryOfficeRoundId = addressListItem.getDeliveryOfficeRoundId();
        section = addressListItem.getSection();
        dpidSequenceNumber = addressListItem.getDpidSequenceNumber();
        geo = addressListItem.getGeo();
    }
}
