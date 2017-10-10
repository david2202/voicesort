package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.AddressListDao;
import au.com.auspost.voicesort.dao.AddressListItemDao;
import au.com.auspost.voicesort.dao.DeviceDao;
import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import au.com.auspost.voicesort.domain.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceService {
    public static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceDao deviceDao;

    public Device load(Integer id) {
        return deviceDao.getOne(id);
    }

    @Cacheable("devices")
    public Device load(String uuid) {
        return deviceDao.findByUuid(uuid);
    }
}
