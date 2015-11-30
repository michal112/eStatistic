package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;

import java.util.List;

public interface CowParentService extends EntityService<CowParent> {

    List<Cow> getCowParentChildren(Long cowParentId);

}
