package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityDao extends JpaRepository<Facility, Integer> {

}
