package au.com.auspost.voicesort.domain;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.service.SpeechSynonymService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakRangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SpeechSynonymValidator implements Validator {
    @Autowired
    private SpeechSynonymService speechSynonymService;

    @Autowired
    private Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return SpeechSynonym.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        validator.validate(o, errors);
        SpeechSynonym speechSynonym = (SpeechSynonym) o;

        validateDuplicates(speechSynonym, errors);
    }

    private boolean validateDuplicates(SpeechSynonym speechSynonym, Errors errors) {
        SpeechSynonym ss = speechSynonymService.loadBySynonym(speechSynonym.getSynonym());

        if (ss != null && ss.getId() != speechSynonym.getId()) {
            errors.rejectValue("synonym", "field.synonym.duplicate", "Synonym already exists");
            return false;
        }
        return true;
    }
}
