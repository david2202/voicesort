package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.AddressListDao;
import au.com.auspost.voicesort.dao.AddressListItemDao;
import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressListService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddressListService.class);

    @Autowired
    private AddressListDao addressListDao;

    @Autowired
    private AddressListItemDao addressListItemDao;

    public AddressList load(Integer id) {
        return addressListDao.getOne(id);
    }

    public AddressList find(Integer postCode, AddressList.Type type, AddressList.Status status) {
        return addressListDao.findByPostCodeAndTypeAndStatusCd(postCode, type, status);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(AddressList addressList) {
        // If we are saving an OPEN list, then close any other list for this round with 'Delivering' status
        if (addressList.getStatusCd() == AddressList.Status.OPEN) {
            LOGGER.info("Saving Address List with 'OPEN'");
            AddressList existingAddressList = addressListDao.findByPostCodeAndTypeAndStatusCd(addressList.getPostCode(), addressList.getType(), AddressList.Status.OPEN);
            if (existingAddressList != null && !existingAddressList.getId().equals(addressList.getId())) {
                LOGGER.info("Closing existing address list {}", existingAddressList.getId());
                existingAddressList.close();
            }
        }
        addressListDao.save(addressList);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void saveItem(AddressListItem addressListItem) {
        addressListItemDao.save(addressListItem);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteItem(Integer addressListItemId) {
        addressListItemDao.delete(addressListItemId);
    }
}

