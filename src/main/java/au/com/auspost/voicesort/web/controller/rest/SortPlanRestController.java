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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping(path = "/rest/api/sortPlan")
public class SortPlanRestController {

    @Autowired
    private SortPlanService sortPlanService;

    @Autowired
    private FacilityService facilityService;

    @RequestMapping(value = "/{id}", method = GET)
    public SortPlanVO get(@PathVariable("id") int id) {
        return SortPlanVO.build(sortPlanService.load(id), true);
    }

    @RequestMapping(value = "/{id}/break", method = GET)
    public SortPlanBreak findBreak(@PathVariable("id") int sortPlanId,
                                   @RequestParam("postCode") Integer postCode) {
        return sortPlanService.findBreak(sortPlanId, postCode);
    }

    @RequestMapping(value = "/", method = GET)
    public List<SortPlanVO> list() {
        List<SortPlanVO> sortPlans = new ArrayList<>();
        sortPlanService.list()
                .forEach(sp -> sortPlans.add(SortPlanVO.build(sp, false)));
        return sortPlans;
    }

    @RequestMapping(value = "/", method = POST)
    public SortPlanVO save(@RequestBody SortPlanVO sortPlanVO, HttpServletResponse response) {
        Facility facility = facilityService.load(sortPlanVO.getFacility().getId());
        if (facility == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        SortPlan sortPlan = sortPlanVO.getId() == null ? new SortPlan() : sortPlanService.load(sortPlanVO.getId());

        sortPlan.setId(sortPlanVO.getId());
        sortPlan.setDescription(sortPlanVO.getDescription());
        sortPlan.setFacility(facility);
        sortPlan.setPrint(sortPlanVO.getPrint());

        sortPlanService.save(sortPlan);
        return SortPlanVO.build(sortPlan, false);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        sortPlanService.delete(id);
    }

    @RequestMapping(path = "/{id}/sortPlanBreak", method = POST)
    public SortPlanBreakVO save(@PathVariable("id") Integer id,
                                @RequestBody SortPlanBreakVO sortPlanBreakVO,
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
