package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.FacilityDao;
import au.com.auspost.voicesort.dao.SortPlanDao;
import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityDao facilityDao;

    public List<Facility> list() {
        return facilityDao.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
