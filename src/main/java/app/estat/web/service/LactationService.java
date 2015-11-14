package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.repository.LactationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LactationService extends AbstractEntityService<LactationRepository, Lactation> {

    @Autowired
    private EntityService<Cow> cowService;

    public void setLactationCow(Long lactationId, Long cowId) {
        Lactation lactation = get(lactationId);
        Cow cow = cowService.get(cowId);

        lactation.setCow(cow);
        cow.getLactations().add(lactation);

        save(lactation);
        cowService.save(cow);
    }

}
