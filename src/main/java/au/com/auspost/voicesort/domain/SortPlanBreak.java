package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class SortPlanBreak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="sort_plan_id")
    @JsonIgnore
    private SortPlan sortPlan;

    @OneToMany(mappedBy = "sortPlanBreak", cascade = CascadeType.ALL, fetch = LAZY)
    @OrderBy("postcodeStart")
    private List<SortPlanBreakRange> sortPlanBreakRanges = new ArrayList<>();

    private String description;

    private String displayOutcome;

    private String spokenOutcome;

    private String printedOutcome;

    public SortPlanBreak add(SortPlanBreakRange sortPlanBreakRange) {
        this.sortPlanBreakRanges.add(sortPlanBreakRange);
        sortPlanBreakRange.setSortPlanBreak(this);
        return this;
    }

    public SortPlanBreak copy() {
        SortPlanBreak sortPlanBreakCopy =  SortPlanBreak.builder()
                .description(this.description)
                .displayOutcome(this.displayOutcome)
                .spokenOutcome(this.spokenOutcome)
                .printedOutcome(this.printedOutcome)
                .sortPlanBreakRanges(new ArrayList<>())
                .build();
        this.sortPlanBreakRanges.forEach(spbr -> sortPlanBreakCopy.add(spbr.copy()));
        return sortPlanBreakCopy;
    }
}
