package au.com.auspost.voicesort.web.controller;

import au.com.auspost.voicesort.service.FacilityService;
import au.com.auspost.voicesort.service.SortPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
