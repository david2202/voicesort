package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @Builder
public class SortPlanBreakVO {
    private Integer id;

    private String description;

    private String displayOutcome;

    private String spokenOutcome;

    @JsonInclude(NON_NULL)
    private String printedOutcome;

    @JsonInclude(NON_EMPTY)
    @Singular private List<SortPlanBreakRangeVO> sortPlanBreakRanges;

    public static SortPlanBreakVO build(SortPlanBreak sortPlanBreak) {
        final List<SortPlanBreakRangeVO> sortPlanBreakRanges = new ArrayList<>();
        sortPlanBreak.getSortPlanBreakRanges().forEach(o -> sortPlanBreakRanges.add(SortPlanBreakRangeVO.build(o)));

        return SortPlanBreakVO.builder()
                .id(sortPlanBreak.getId())
                .description(sortPlanBreak.getDescription())
                .displayOutcome(sortPlanBreak.getDisplayOutcome())
                .spokenOutcome(sortPlanBreak.getSpokenOutcome())
                .printedOutcome(sortPlanBreak.getPrintedOutcome())
                .sortPlanBreakRanges(sortPlanBreakRanges)
                .build();
    }
}
