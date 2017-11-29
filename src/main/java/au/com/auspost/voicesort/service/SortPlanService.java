package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.SortPlanDao;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortPlanService {
    @Autowired
    private SortPlanDao sortPlanDao;

    public SortPlanBreak findBreak(Integer sortPlanId, Integer postcode) {
        SortPlan sortPlan = sortPlanDao.getOne(sortPlanId);
        SortPlanBreak result = null;
        for (SortPlanBreak spBreak : sortPlan.getSortPlanBreaks()) {
            for (SortPlanBreakRange range : spBreak.getSortPlanBreakRanges()) {
                if (range.getPostcodeStart().equals(postcode)) {
                    result = spBreak;
                    break;
                } else if (range.getPostcodeStart() < postcode && range.getPostcodeEnd() != null && range.getPostcodeEnd() >= postcode) {
                    result = spBreak;
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public SortPlan load(int id) {
        return sortPlanDao.getOne(id);
    }
}
