package app.estat.web.service;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.repository.InseminationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InseminationService extends AbstractEntityService<InseminationRepository, Insemination> {

    @Autowired
    private EntityService<Cow> cowService;

    @Autowired
    private EntityService<Bull> bullService;

    public void setInseminationCow(Long inseminationId, Long cowId) {
        Insemination insemination = get(inseminationId);
        Cow cow = cowService.get(cowId);

        insemination.setCow(cow);
        cow.getInseminations().add(insemination);

        save(insemination);
        cowService.save(cow);
    }

    public void setInseminationBull(Long inseminationId, Long bullId) {
        Insemination insemination = get(inseminationId);
        Bull bull = bullService.get(bullId);

        insemination.setBull(bull);
        bull.getInseminations().add(insemination);

        save(insemination);
        bullService.save(bull);
    }

}