package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.domain.Facility;
import au.com.auspost.voicesort.domain.SortPlan;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import au.com.auspost.voicesort.service.SpeechSynonymService;
import au.com.auspost.voicesort.web.controller.rest.value.SortPlanVO;
import au.com.auspost.voicesort.web.controller.rest.value.SpeechSynonymVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/speechSynonym/{id}", method = GET)
    public SpeechSynonymVO get(@PathVariable("id") int id) {
        SpeechSynonym speechSynonym = speechSynonymService.load(id);
        return SpeechSynonymVO.build(speechSynonym);
    }

    @RequestMapping(value = "/speechSynonym", method = POST)
    public SpeechSynonymVO save(@RequestBody @Valid SpeechSynonymVO speechSynonymVO, HttpServletResponse response) {
        SpeechSynonym speechSynonym = speechSynonymVO.getId() == null ? new SpeechSynonym() : speechSynonymService.load(speechSynonymVO.getId());

        speechSynonym.setId(speechSynonymVO.getId());
        speechSynonym.setSpeechWord(speechSynonymVO.getSpeechWord());
        speechSynonym.setSynonym(speechSynonymVO.getSynonym());

        speechSynonymService.save(speechSynonym);

        return SpeechSynonymVO.build(speechSynonym);
    }

    @RequestMapping(value = "/speechSynonym/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        speechSynonymService.delete(id);
    }
}
