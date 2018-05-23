package au.com.auspost.voicesort.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SortPlanController {

    @RequestMapping(path = "/sortplan", method = RequestMethod.GET)
    public String execute() {
        return "sortplan";
    }

    @RequestMapping(path = "/sortplans", method = RequestMethod.GET)
    public String maintain() {
        return "sortplans";
    }
}
