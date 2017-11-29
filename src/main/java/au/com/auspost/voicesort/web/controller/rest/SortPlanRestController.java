package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.AddressList;
import au.com.auspost.voicesort.domain.AddressListItem;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.service.AddressListService;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.value.AddressListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/rest/api/sortPlan")
public class SortPlanRestController {

    @Autowired
    private SortPlanService sortPlanService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SortPlan get(@PathVariable("id") int id) {
        return sortPlanService.load(id);
    }

    @RequestMapping(value = "/{id}/break", method = RequestMethod.GET)
    public SortPlanBreak findBreak(@PathVariable("id") int sortPlanId,
                                   @RequestParam("postCode") Integer postCode) {
        return sortPlanService.findBreak(sortPlanId, postCode);
    }
}
