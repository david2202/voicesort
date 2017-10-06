package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressListItemDao extends JpaRepository<AddressListItem, Integer> {

}
