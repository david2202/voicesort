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

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/rest/api/sortPlanBreak")
public class SortPlanBreakRestController {

    @Autowired
    private SortPlanService sortPlanService;

    @RequestMapping(value = "/{id}", method = GET)
    public SortPlanBreakVO get(@PathVariable("id") int id) {
        return SortPlanBreakVO.build(sortPlanService.loadBreak(id));
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        sortPlanService.deleteBreak(id);
    }
}
