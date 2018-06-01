package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @Builder
public class SortPlanBreakRangeVO {
    private Integer id;

    private Integer postcodeStart;

    @JsonInclude(NON_NULL)
    private Integer postcodeEnd;

    public static SortPlanBreakRangeVO build(SortPlanBreakRange sortPlanBreakRange) {
        return SortPlanBreakRangeVO.builder()
                .id(sortPlanBreakRange.getId())
                .postcodeStart(sortPlanBreakRange.getPostcodeStart())
                .postcodeEnd(sortPlanBreakRange.getPostcodeEnd())
                .build();
    }
}
