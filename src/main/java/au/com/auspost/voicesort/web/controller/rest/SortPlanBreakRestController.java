package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.service.FacilityService;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakVO;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/rest/api")
public class SortPlanBreakRestController {

    @Autowired
    private SortPlanService sortPlanService;

    @RequestMapping(value = "/sortPlanBreak/{id}", method = GET)
    public SortPlanBreakVO get(@PathVariable("id") int id) {
        return SortPlanBreakVO.build(sortPlanService.loadBreak(id));
    }

    @RequestMapping(value = "/sortPlanBreak/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        sortPlanService.deleteBreak(id);
    }

    @RequestMapping(path = "/sortPlan/{id}/break", method = POST)
    public SortPlanBreakVO save(@PathVariable("id") Integer id,
                                @RequestBody @Valid SortPlanBreakVO sortPlanBreakVO,
                                HttpServletResponse response) {
        SortPlan sortPlan = sortPlanService.load(id);
        if (sortPlan == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        SortPlanBreak sortPlanBreak = sortPlanBreakVO.getId() == null ? new SortPlanBreak() : sortPlanService.loadBreak(sortPlanBreakVO.getId());

        sortPlanBreak.setId(sortPlanBreakVO.getId());
        sortPlanBreak.setSortPlan(sortPlan);
        sortPlanBreak.setDescription(sortPlanBreakVO.getDescription());
        sortPlanBreak.setDisplayOutcome(sortPlanBreakVO.getDisplayOutcome());
        sortPlanBreak.setSpokenOutcome(sortPlanBreakVO.getSpokenOutcome());
        sortPlanBreak.setPrintedOutcome(sortPlanBreakVO.getPrintedOutcome());

        sortPlanService.saveBreak(sortPlanBreak);
        return SortPlanBreakVO.build(sortPlanBreak);
    }

}
