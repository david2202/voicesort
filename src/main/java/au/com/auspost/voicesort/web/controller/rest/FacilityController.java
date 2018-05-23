package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.service.FacilityService;
import au.com.auspost.voicesort.service.SortPlanService;
import au.com.auspost.voicesort.web.controller.rest.value.FacilityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/rest/api/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @RequestMapping(name = "/", method = GET)
    public List<FacilityVO> list() {
        List<Facility> facilities = facilityService.list();
        List<FacilityVO> facilityVOs = new ArrayList<>();
        facilities.forEach(f -> facilityVOs.add(new FacilityVO(f)));
        return facilityVOs;
    }
}
