package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.entity.Lactation;

import java.util.List;

public interface CowService extends EntityService<Cow> {

    void setCowParent(Long cowId, Long cowParentId);
    CowParent getCowParent(Long cowId);
    List<Lactation> getCowLactations(Long cowId);
    List<Insemination> getCowInseminations(Long cowId);

}
