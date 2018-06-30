package au.com.auspost.voicesort.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class SpeechSynonym extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String speechWord;

    private String synonym;
}
