package au.com.auspost.voicesort.web.controller.rest.value;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakRangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SortPlanBreakRangeValidator implements Validator {
    @Autowired
    private SortPlanService sortPlanService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SortPlanBreakRangeVO.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        SortPlanBreakRangeVO spbr = (SortPlanBreakRangeVO) o;

        if (!validatePostcodeStartLessThanPostcodeEnd(spbr, errors)) {
            return;
        } else {
           validateOverlappingPostcodeRange(spbr, errors);
        }
    }

    private boolean validatePostcodeStartLessThanPostcodeEnd(SortPlanBreakRangeVO spbr, Errors errors) {
        if (spbr.getPostcodeEnd() != null && spbr.getPostcodeStart() > spbr.getPostcodeEnd()) {
            errors.rejectValue("postcodeStart", "field.postcodeStart.gtPostcodeEnd", "Postcode Start must be less than Postcode End");
            return false;
        }
        return true;
    }

    private boolean validateOverlappingPostcodeRange(SortPlanBreakRangeVO spbr, Errors errors) {
        SortPlanBreak spb = sortPlanService.loadBreak(spbr.getSortPlanBreakId());
        int spbrPostCodeEnd = spbr.getPostcodeEnd() == null ? spbr.getPostcodeStart() : spbr.getPostcodeEnd();

        for (SortPlanBreak spbOther : spb.getSortPlan().getSortPlanBreaks()) {
            for (SortPlanBreakRange spbrOther: spbOther.getSortPlanBreakRanges()) {
                if (!spbrOther.getId().equals(spbr.getId())) {
                    int spbrOtherPostCodeEnd = spbrOther.getPostcodeEnd() == null ? spbrOther.getPostcodeStart() : spbrOther.getPostcodeEnd();
                    if ((spbr.getPostcodeStart() >= spbrOther.getPostcodeStart()
                            && spbr.getPostcodeStart() <= spbrOtherPostCodeEnd)) {
                        errors.rejectValue("postcodeStart", "field.postcodeStart.overlapping", "Overlap with break "
                                + spbOther.getDescription() + " "
                                + spbrOther.getPostcodeStart()
                                + (spbrOther.getPostcodeEnd() == null ? "" : "-" + spbrOther.getPostcodeEnd()));
                        return false;
                    } else if ((spbrPostCodeEnd >= spbrOther.getPostcodeStart()
                            && spbrPostCodeEnd <= spbrOtherPostCodeEnd)) {
                        errors.rejectValue("postcodeEnd", "field.postcodeEnd.overlapping", "Overlap with break "
                                + spbOther.getDescription() + " "
                                + spbrOther.getPostcodeStart()
                                + (spbrOther.getPostcodeEnd() == null ? "" : "-" + spbrOther.getPostcodeEnd()));
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
