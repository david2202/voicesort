package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakRangeValidator;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakRangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/rest/api")
public class SortPlanBreakRangeRestController {

    @Autowired
    private SortPlanBreakRangeValidator sortPlanBreakRangeValidator;

    @Autowired
    private SortPlanService sortPlanService;

    @InitBinder("sortPlanBreakRangeVO")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(sortPlanBreakRangeValidator);
    }

    @RequestMapping(value = "/sortPlanBreakRange/{id}", method = GET)
    public SortPlanBreakRangeVO get(@PathVariable("id") int id) {
        return SortPlanBreakRangeVO.build(sortPlanService.loadBreakRange(id));
    }

    @RequestMapping(value = "/sortPlanBreakRange/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        sortPlanService.deleteBreakRange(id);
    }


    @RequestMapping(path = "/sortPlanBreak/{id}/range", method = POST)
    public SortPlanBreakRangeVO save(@PathVariable("id") Integer id,
                                     @Valid @RequestBody SortPlanBreakRangeVO sortPlanBreakRangeVO,
                                     HttpServletResponse response) {
        SortPlanBreak sortPlanBreak = sortPlanService.loadBreak(id);
        if (sortPlanBreak == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        SortPlanBreakRange sortPlanBreakRange = sortPlanBreakRangeVO.getId() == null
                ? new SortPlanBreakRange()
                : sortPlanService.loadBreakRange(sortPlanBreakRangeVO.getId());

        sortPlanBreakRange.setId(sortPlanBreakRangeVO.getId());
        sortPlanBreakRange.setSortPlanBreak(sortPlanBreak);
        sortPlanBreakRange.setPostcodeStart(sortPlanBreakRangeVO.getPostcodeStart());
        sortPlanBreakRange.setPostcodeEnd(sortPlanBreakRangeVO.getPostcodeEnd());

        sortPlanService.saveBreakRange(sortPlanBreakRange);
        return SortPlanBreakRangeVO.build(sortPlanBreakRange);
    }

}
