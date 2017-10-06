package au.com.auspost.voicesort.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LearnController {

    @RequestMapping(path = "/learn", method = RequestMethod.GET)
    public String get() {
        return "learn";
    }
}
