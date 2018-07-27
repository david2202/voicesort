package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Sort;
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
public class SortPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name="facility_id")
    @Getter @Setter
    private Facility facility;

    @OneToMany(mappedBy = "sortPlan", cascade = CascadeType.ALL, fetch = LAZY)
    @OrderBy("description")
    @Getter @Setter
    private List<SortPlanBreak> sortPlanBreaks = new ArrayList<>();

    @Getter @Setter
    private String description;

    @Getter @Setter
    @Column(name = "print_ind")
    private Boolean print;

    public SortPlan add(SortPlanBreak sortPlanBreak) {
        this.sortPlanBreaks.add(sortPlanBreak);
        sortPlanBreak.setSortPlan(this);
        return this;
    }

    public SortPlan copy() {
        SortPlan sortPlanCopy = SortPlan.builder()
                .facility(this.getFacility())
                .description(this.description)
                .print(this.print)
                .sortPlanBreaks(new ArrayList<>())
                .build();
        this.sortPlanBreaks.forEach(spb -> sortPlanCopy.add(spb.copy()));
        return sortPlanCopy;
    }
}
