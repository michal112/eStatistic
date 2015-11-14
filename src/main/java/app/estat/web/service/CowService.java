package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CowService extends AbstractEntityService<CowRepository, Cow> {

    @Autowired
    private EntityService<CowParent> cowParentService;

    public void setCowParent(Long cowId, Long cowParentId) {
        Cow cow = get(cowId);
        CowParent cowParent = cowParentService.get(cowParentId);

        cow.setParent(cowParent);
        cowParent.getChildren().add(cow);

        save(cow);
        cowParentService.save(cowParent);
    }

}
