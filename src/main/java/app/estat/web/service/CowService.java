package app.estat.web.service;

import app.estat.web.model.entity.Cow;

public interface CowService extends EntityService<Cow> {

    void setCowParent(Long cowId, Long cowParentId);

}
