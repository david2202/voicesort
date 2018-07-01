package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.SpeechSynonymDao;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import au.com.auspost.voicesort.domain.SpeechSynonymValidator;
import au.com.auspost.voicesort.web.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class SpeechSynonymService {

    @Autowired
    private SpeechSynonymDao speechSynonymDao;

    @Autowired
    private SpeechSynonymValidator speechSynonymValidator;

    public List<SpeechSynonym> list() {
        return speechSynonymDao.findAllByOrderBySpeechWordAsc();
    }

    public SpeechSynonym load(Integer id) {
        return speechSynonymDao.getOne(id);
    }

    public SpeechSynonym loadBySynonym(String synonym) {
        return speechSynonymDao.findOneBySynonym(synonym);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(SpeechSynonym speechSynonym) {
        BindingResult errors = new BeanPropertyBindingResult(speechSynonym, "speechSynonym");
        speechSynonymValidator.validate(speechSynonym, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        } else {
            speechSynonymDao.save(speechSynonym);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Integer id) {
        speechSynonymDao.deleteById(id);
    }
}
