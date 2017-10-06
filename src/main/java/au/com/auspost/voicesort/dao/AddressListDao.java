package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressListDao extends JpaRepository<AddressList, Integer> {
    AddressList findByPostCodeAndStatusCd(Integer postCode, AddressList.Status status);
}
