package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao extends JpaRepository<Device, Integer> {
    public Device findByUuid(String uuid);
}
