package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter @Setter
public class SortPlanBreakRangeVO {
    private Integer id;

    private Integer postcodeStart;

    @JsonInclude(NON_NULL)
    private Integer postcodeEnd;

    public SortPlanBreakRangeVO(SortPlanBreakRange sortPlanBreakRange) {
        this.id = sortPlanBreakRange.getId();
        this.postcodeStart = sortPlanBreakRange.getPostcodeStart();
        this.postcodeEnd = sortPlanBreakRange.getPostcodeEnd();
    }
}
