package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CowServiceImpl extends AbstractEntityServiceImpl<CowRepository, Cow>
        implements CowService {

    @Autowired
    private CowParentService cowParentService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void setCowParent(Long cowId, Long cowParentId) {
        Cow cow = get(cowId);
        CowParent cowParent = cowParentService.get(cowParentId);

        cow.setParent(cowParent);
        cowParent.getChildren().add(cow);

        save(cow);
        cowParentService.save(cowParent);
    }

}
