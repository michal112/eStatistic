package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.repository.LactationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LactationServiceImpl extends AbstractEntityServiceImpl<LactationRepository, Lactation>
        implements LactationService {

    @Autowired
    private CowService cowService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void setLactationCow(Long lactationId, Long cowId) {
        Lactation lactation = get(lactationId);
        Cow cow = cowService.get(cowId);

        lactation.setCow(cow);
        cow.getLactations().add(lactation);

        save(lactation);
        cowService.save(cow);
    }

    @Override
    public Cow getLactationCow(Long lactationId) {
        Lactation lactation = get(lactationId);

        return lactation.getCow();
    }

}
