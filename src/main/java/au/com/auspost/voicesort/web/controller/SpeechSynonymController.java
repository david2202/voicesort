package au.com.auspost.voicesort.web.controller;

import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.service.FacilityService;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.service.SpeechSynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpeechSynonymController {
    @Autowired
    private SpeechSynonymService speechSynonymService;

    @RequestMapping(path = "/speechSynonyms", method = RequestMethod.GET)
    public String execute() {
        return "speechSynonyms";
    }
}
