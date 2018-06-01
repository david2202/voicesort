package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlan;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class SortPlanVO {
    private Integer id;

    private String description;

    private Boolean print;

    private FacilityVO facility;

    @JsonInclude(NON_EMPTY)
    private List<SortPlanBreakVO> sortPlanBreaks;

    public static SortPlanVO build(SortPlan sortPlan, boolean includeBreaks) {
        final List<SortPlanBreakVO> sortPlanBreaks = new ArrayList<>();
        if (includeBreaks) {
            sortPlan.getSortPlanBreaks().forEach(o -> sortPlanBreaks.add(SortPlanBreakVO.build(o)));
        }

        return SortPlanVO.builder()
                .id(sortPlan.getId())
                .description(sortPlan.getDescription())
                .print(sortPlan.getPrint())
                .facility(FacilityVO.builder()
                        .id(sortPlan.getFacility().getId())
                        .name(sortPlan.getFacility().getName())
                        .build()
                )
                .sortPlanBreaks(sortPlanBreaks)
                .build();
    }
}
