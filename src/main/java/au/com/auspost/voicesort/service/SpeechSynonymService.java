package au.com.auspost.voicesort.service;

import au.com.auspost.voicesort.dao.SpeechSynonymDao;
import au.com.auspost.voicesort.domain.SpeechSynonym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeechSynonymService {

    @Autowired
    private SpeechSynonymDao speechSynonymDao;

    public List<SpeechSynonym> list() {
        return speechSynonymDao.findAll();
    }
}
