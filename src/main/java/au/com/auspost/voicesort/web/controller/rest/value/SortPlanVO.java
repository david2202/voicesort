package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Getter @Setter
public class SortPlanVO {
    private Integer id;

    private String description;

    private Boolean print;

    private List<SortPlanBreakVO> sortPlanBreaks;

    public SortPlanVO(SortPlan sortPlan) {
        this.id = sortPlan.getId();
        this.description = sortPlan.getDescription();
        this.print = sortPlan.getPrint();

        if (sortPlan.getSortPlanBreaks() != null) {
            sortPlanBreaks = new ArrayList<>();
            for (SortPlanBreak spb : sortPlan.getSortPlanBreaks()) {
                sortPlanBreaks.add(new SortPlanBreakVO(spb));
            }
        }
    }
}
