package app.estat.web.service;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Insemination;

public interface InseminationService extends EntityService<Insemination> {

    void setInseminationCow(Long inseminationId, Long cowId);
    Cow getInseminationCow(Long inseminationId);
    void setInseminationBull(Long inseminationId, Long bullId);
    Bull getInseminationBull(Long inseminationId);

}
