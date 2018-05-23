package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class SortPlanBreak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name="sort_plan_id")
    @JsonIgnore
    @Getter @Setter
    private SortPlan sortPlan;

    @OneToMany(mappedBy = "sortPlanBreak", cascade = CascadeType.ALL)
    @OrderBy("postcodeStart")
    @Getter @Setter
    private List<SortPlanBreakRange> sortPlanBreakRanges = new ArrayList<>();

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String displayOutcome;

    @Getter @Setter
    private String spokenOutcome;

    @Getter @Setter
    private String printedOutcome;
}
