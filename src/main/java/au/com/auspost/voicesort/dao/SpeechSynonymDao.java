package au.com.auspost.voicesort.dao;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeechSynonymDao extends JpaRepository<SpeechSynonym, Integer> {
    List<SpeechSynonym> findAllByOrderBySynonymAsc();
}
