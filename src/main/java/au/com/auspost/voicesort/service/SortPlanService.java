package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.SortPlanBreakDao;
import au.com.auspost.voicesort.dao.SortPlanBreakRangeDao;
import au.com.auspost.voicesort.dao.SortPlanDao;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortPlanService {
    @Autowired
    private SortPlanDao sortPlanDao;

    @Autowired
    private SortPlanBreakDao sortPlanBreakDao;

    @Autowired
    private SortPlanBreakRangeDao sortPlanBreakRangeDao;

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

    public SortPlanBreak loadBreak(int id) {
        return sortPlanBreakDao.getOne(id);
    }

    public SortPlanBreakRange loadBreakRange(int id) {
        return sortPlanBreakRangeDao.getOne(id);
    }

    public List<SortPlan> list() {
        return sortPlanDao.findAll(new Sort(Sort.Direction.ASC, "description"));
    }

    public void save(SortPlan sortPlan) {
        sortPlanDao.save(sortPlan);
    }

    public void saveBreak(SortPlanBreak sortPlanBreak) {
        sortPlanBreakDao.save(sortPlanBreak);
    }

    public void saveBreakRange(SortPlanBreakRange sortPlanBreakRange) {
        sortPlanBreakRangeDao.save(sortPlanBreakRange);
    }

    public void delete(Integer id) {
        sortPlanDao.deleteById(id);
    }

    public void deleteBreak(Integer id) {
        sortPlanBreakDao.deleteById(id);
    }

    public void deleteBreakRange(Integer id) {
        sortPlanBreakRangeDao.deleteById(id);
    }
}
