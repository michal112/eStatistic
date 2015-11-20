package app.estat.web.service;

import app.estat.web.model.entity.Insemination;

public interface InseminationService extends EntityService<Insemination> {

    void setInseminationCow(Long inseminationId, Long cowId);
    void setInseminationBull(Long inseminationId, Long bullId);

}
