package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter @Setter
public class SortPlanBreakVO {
    private Integer id;

    private String description;

    private String displayOutcome;

    private String spokenOutcome;

    @JsonInclude(NON_NULL)
    private String printedOutcome;

    private List<SortPlanBreakRangeVO> sortPlanBreakRanges;

    public SortPlanBreakVO(SortPlanBreak sortPlanBreak) {
        this.id = sortPlanBreak.getId();
        this.description = sortPlanBreak.getDescription();
        this.displayOutcome = sortPlanBreak.getDisplayOutcome();
        this.spokenOutcome = sortPlanBreak.getSpokenOutcome();
        this.printedOutcome = sortPlanBreak.getPrintedOutcome();

        if (sortPlanBreak.getSortPlanBreakRanges() != null) {
            sortPlanBreakRanges = new ArrayList<>();
            for (SortPlanBreakRange spbr : sortPlanBreak.getSortPlanBreakRanges()) {
                sortPlanBreakRanges.add(new SortPlanBreakRangeVO(spbr));
            }
        }
    }
}
