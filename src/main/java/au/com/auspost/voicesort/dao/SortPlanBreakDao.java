package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortPlanBreakDao extends JpaRepository<SortPlanBreak, Integer> {

}
