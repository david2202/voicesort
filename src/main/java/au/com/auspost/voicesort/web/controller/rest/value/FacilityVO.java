package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.Facility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class FacilityVO {
    private Integer id;

    private String name;

    @JsonInclude(NON_EMPTY)
    @Singular
    private List<SortPlanVO> sortPlans;

    public static FacilityVO build(Facility facility) {
        final List<SortPlanVO> sortPlans = new ArrayList<>();
        facility.getSortPlans().forEach(o -> sortPlans.add(SortPlanVO.build(o, true)));

        return FacilityVO.builder()
                .id(facility.getId())
                .name(facility.getName())
                .sortPlans(sortPlans)
                .build();
    }
}
