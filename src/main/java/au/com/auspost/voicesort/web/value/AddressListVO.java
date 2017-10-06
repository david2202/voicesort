package au.com.auspost.voicesort.web.value;

import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AddressListVO {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private List<AddressListItemVO> addressListItems = new ArrayList<>();

    @Getter @Setter
    private Integer postCode;

    @Getter @Setter
    private AddressList.Status statusCd;

    @Getter @Setter
    private AddressList.Type type;

    public AddressListVO(AddressList addressList) {
        this.id = addressList.getId();
        this.postCode = addressList.getPostCode();
        this.statusCd = addressList.getStatusCd();
        this.type = addressList.getType();

        for (AddressListItem addressListItem : addressList.getAddressListItems()) {
            AddressListItemVO addressListItemVO = new AddressListItemVO(addressListItem);
            addressListItems.add(addressListItemVO);
        }
    }
}
