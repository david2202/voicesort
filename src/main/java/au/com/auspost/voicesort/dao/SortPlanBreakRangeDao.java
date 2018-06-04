package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortPlanBreakRangeDao extends JpaRepository<SortPlanBreakRange, Integer> {

}
