package au.com.auspost.voicesort.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Facility extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = LAZY)
    @OrderBy("description")
    @Getter @Setter
    private List<SortPlan> sortPlans = new ArrayList<>();

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String code;
}
