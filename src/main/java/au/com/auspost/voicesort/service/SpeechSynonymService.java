package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.SpeechSynonymDao;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpeechSynonymService {

    @Autowired
    private SpeechSynonymDao speechSynonymDao;

    public List<SpeechSynonym> list() {
        return speechSynonymDao.findAllByOrderBySynonymAsc();
    }

    public SpeechSynonym load(Integer id) {
        return speechSynonymDao.getOne(id);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(SpeechSynonym speechSynonym) {
        speechSynonymDao.save(speechSynonym);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Integer id) {
        speechSynonymDao.deleteById(id);
    }
}
