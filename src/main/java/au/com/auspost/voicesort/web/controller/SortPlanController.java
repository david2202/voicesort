package au.com.auspost.voicesort.web.controller;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.service.FacilityService;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakVO;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SortPlanController {
    @Autowired
    private SortPlanService sortPlanService;

    @Autowired
    private FacilityService facilityService;

    @RequestMapping(path = "/sortplan", method = RequestMethod.GET)
    public String execute() {
        return "sortplan";
    }

    @RequestMapping(path = "/sortplans", method = RequestMethod.GET)
    public ModelAndView maintain() {
        ModelAndView mav = new ModelAndView("sortplans");
        mav.addObject("facilities", facilityService.list());
        return mav;
    }

    @RequestMapping(path = "/sortplan/{id}/breaks", method = RequestMethod.GET)
    public ModelAndView sortPlanBreaks(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("sortplanbreaks");
        SortPlan sortPlan = sortPlanService.load(id);
        mav.addObject("sortPlan", sortPlan);
        return mav;
    }

    @RequestMapping(path = "/sortplan/{id}/break/{breakId}/ranges", method = RequestMethod.GET)
    public ModelAndView sortPlanBreakRanges(@PathVariable("id") Integer id,
                                            @PathVariable("breakId") Integer sortPlanBreakId) {
        ModelAndView mav = new ModelAndView("sortplanbreakranges");
        SortPlanBreak sortPlanBreak = sortPlanService.loadBreak(sortPlanBreakId);
        mav.addObject("sortPlanBreak", sortPlanBreak);
        return mav;
    }
}
