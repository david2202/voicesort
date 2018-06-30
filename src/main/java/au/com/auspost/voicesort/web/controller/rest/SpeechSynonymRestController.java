package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.SpeechSynonym;
import au.com.auspost.voicesort.service.SpeechSynonymService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanVO;
import au.com.auspost.voicesort.web.controller.rest.value.SpeechSynonymVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/rest/api")
public class SpeechSynonymRestController {
    @Autowired
    private SpeechSynonymService speechSynonymService;

    @RequestMapping(value = "/speechSynonyms", method = GET)
    public List<SpeechSynonymVO> list() {
        List<SpeechSynonymVO> speechSynonyms = new ArrayList<>();
        speechSynonymService.list()
                .forEach(ss -> speechSynonyms.add(SpeechSynonymVO.build(ss)));
        return speechSynonyms;
    }
}
