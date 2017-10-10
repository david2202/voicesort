package au.com.auspost.voicesort.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HealthCheckController {

    @RequestMapping(path = "/healthCheck", method = RequestMethod.GET)
    public String get() {
        return "healthCheck";
    }
}
