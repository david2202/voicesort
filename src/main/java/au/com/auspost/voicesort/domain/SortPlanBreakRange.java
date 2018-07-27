package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class SortPlanBreakRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="sort_plan_break_id")
    @JsonIgnore
    private SortPlanBreak sortPlanBreak;

    private Integer postcodeStart;

    private Integer postcodeEnd;

    public SortPlanBreakRange copy() {
        return SortPlanBreakRange.builder()
                .postcodeStart(this.postcodeStart)
                .postcodeEnd(this.getPostcodeEnd())
                .build();
    }
}
