package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.SortPlanBreak;
import au.com.auspost.voicesort.domain.SortPlanBreakRange;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakRangeVO;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanBreakVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/rest/api")
public class SortPlanBreakRangeRestController {

    @Autowired
    private SortPlanService sortPlanService;

    @RequestMapping(value = "/sortPlanBreakRange/{id}", method = GET)
    public SortPlanBreakRangeVO get(@PathVariable("id") int id) {
        return SortPlanBreakRangeVO.build(sortPlanService.loadBreakRange(id));
    }

    @RequestMapping(value = "/sortPlanBreakRange/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        sortPlanService.deleteBreakRange(id);
    }


    @RequestMapping(path = "/sortPlanBreak/{id}/range", method = POST)
    public SortPlanBreakRangeVO save(@PathVariable("id") Integer id,
                                     @RequestBody SortPlanBreakRangeVO sortPlanBreakRangeVO,
                                     HttpServletResponse response) {
        SortPlanBreak sortPlanBreak = sortPlanService.loadBreak(id);
        if (sortPlanBreak == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        SortPlanBreakRange sortPlanBreakRange = sortPlanBreakRangeVO.getId() == null
                ? new SortPlanBreakRange()
                : sortPlanService.loadBreakRange(sortPlanBreakRangeVO.getId());

        sortPlanBreakRange.setId(sortPlanBreakRangeVO.getId());
        sortPlanBreakRange.setSortPlanBreak(sortPlanBreak);
        sortPlanBreakRange.setPostcodeStart(sortPlanBreakRangeVO.getPostcodeStart());
        sortPlanBreakRange.setPostcodeEnd(sortPlanBreakRangeVO.getPostcodeEnd());

        sortPlanService.saveBreakRange(sortPlanBreakRange);
        return SortPlanBreakRangeVO.build(sortPlanBreakRange);
    }

}
