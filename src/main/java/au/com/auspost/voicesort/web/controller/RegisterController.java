package au.com.auspost.voicesort.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView get() {
        Map<String,Object> model = new HashMap<>();
        model.put("uuid", UUID.randomUUID().toString());
        return new ModelAndView("register", model);
    }
}
