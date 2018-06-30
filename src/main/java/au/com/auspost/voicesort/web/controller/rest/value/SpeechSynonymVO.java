package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeechSynonymVO {
    private Integer id;

    private String speechWord;

    private String synonym;

    public static SpeechSynonymVO build(SpeechSynonym speechSynonym) {
        return SpeechSynonymVO.builder()
                .id(speechSynonym.getId())
                .speechWord(speechSynonym.getSpeechWord())
                .synonym(speechSynonym.getSynonym())
                .build();

    }
}