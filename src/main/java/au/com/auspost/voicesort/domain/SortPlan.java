package au.com.auspost.voicesort.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class SortPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="facility_id")
    private Facility facility;

    @OneToMany(mappedBy = "sortPlan", cascade = CascadeType.ALL, fetch = LAZY)
    @OrderBy("description")
    private List<SortPlanBreak> sortPlanBreaks = new ArrayList<>();

    private String description;

    @Column(name = "print_ind")
    private Boolean print;
}
