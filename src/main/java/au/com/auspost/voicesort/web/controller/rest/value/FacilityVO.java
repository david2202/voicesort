package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class FacilityVO {
    private Integer id;

    private String name;

    private List<SortPlanVO> sortPlans;

    public FacilityVO(Facility facility) {
        this.id = facility.getId();
        this.name = facility.getName();
        if (facility.getSortPlans() != null) {
            sortPlans = new ArrayList<>();
            for (SortPlan sp : facility.getSortPlans()) {
                sortPlans.add(new SortPlanVO(sp));
            }
        }
    }
}
